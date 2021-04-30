package br.com.lucaspires.tibiainfotracker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.lucaspires.tibiainfotracker.AppConstants.FIREBASE_DATABASE_REFERENCE_CITIES
import br.com.lucaspires.tibiainfotracker.AppConstants.FIREBASE_DATABASE_REFERENCE_SUPPORTERS
import br.com.lucaspires.tibiainfotracker.AppConstants.FIREBASE_DATABASE_REFERENCE_WORLDS
import br.com.lucaspires.tibiainfotracker.data.api.TibiaDataService
import br.com.lucaspires.tibiainfotracker.data.firebase.FirebaseListeners
import br.com.lucaspires.tibiainfotracker.data.firebase.NO_CONNECTION_FIREBASE_ERROR
import br.com.lucaspires.tibiainfotracker.data.local.dao.FavoriteDAO
import br.com.lucaspires.tibiainfotracker.data.local.entity.FavoriteEntity
import br.com.lucaspires.tibiainfotracker.data.model.houses.HousesResponse
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import com.google.firebase.database.DatabaseReference
import io.reactivex.Completable
import io.reactivex.Single

//todo melhorar ou remover firebase
internal class RepositoryImp(
    private val service: TibiaDataService,
    private val firebaseData: DatabaseReference,
    private val favoriteDAO: FavoriteDAO
) : Repository {
    override fun getCharacter(name: String) = service.getCharacter(name)
    override fun getNews() = service.getNews()

    override fun getWorlds(): Single<List<String>> {
        return Single.create { stream ->
            val firebaseListeners = FirebaseListeners(stream)
            firebaseData.child(FIREBASE_DATABASE_REFERENCE_WORLDS).get()
                .addOnSuccessListener(firebaseListeners)
                .addOnFailureListener(firebaseListeners)
        }
    }

    override fun getCities(): Single<List<String>> {
        return Single.create { stream ->
            val firebaseListeners = FirebaseListeners(stream)
            firebaseData.child(FIREBASE_DATABASE_REFERENCE_CITIES).get()
                .addOnSuccessListener(firebaseListeners)
        }
    }

    override fun getHouses(
        world: String,
        town: String,
        isGuildHouse: Boolean
    ): Single<HousesResponse> {
        return if (isGuildHouse) {
            service.getGuildHouses(world = world, town = town)
        } else {
            service.getHouses(world = world, town = town)
        }
    }

    override fun getHouseDetail(
        world: String,
        houseId: Int,
    ) = service.getHouseDetails(world, houseId)

    override fun getHighscores(
        world: String,
        rankType: String,
        vocation: String
    ) = service.getHighscores(world = world, rankType = rankType, vocation = vocation)

    override fun getAllFavorites(): LiveData<List<String>> {
        return Transformations.map(favoriteDAO.getAll()) { list ->
            list.map { it.name }
        }
    }

    override fun isFavoritedChar(name: String): Single<Boolean> {
        return favoriteDAO.getCharacter(name).isEmpty
            .map { it.not() }
    }

    override fun setFavorite(name: String): Completable {
        return favoriteDAO.getCharacter(name)
            .isEmpty
            .flatMapCompletable {
                if (it) insertChar(name) else deleteChar(name)
            }
    }

    //todo colocar no firebase listener como generics
    override fun getSuppSites(): Single<List<SupportModel>> {
        return Single.create { stream ->
            firebaseData.child(FIREBASE_DATABASE_REFERENCE_SUPPORTERS).get()
                .addOnSuccessListener { data ->
                    val suppList = arrayListOf<SupportModel>()
                    data?.children?.forEach { item ->
                        item.getValue(SupportModel::class.java)?.let {
                            suppList.add(it)
                        }
                    }
                    stream.onSuccess(suppList)
                }
                .addOnFailureListener { ex ->
                    ex.message?.let {
                        if (it.contains(NO_CONNECTION_FIREBASE_ERROR)) {
                            stream.onError(TibiaException.NoConnection)
                            return@addOnFailureListener
                        }
                    }
                    stream.onError(ex)
                }
        }
    }

    private fun insertChar(name: String) = favoriteDAO.insertCharacter(FavoriteEntity(name))
    private fun deleteChar(name: String) = favoriteDAO.deleteCharacter(FavoriteEntity(name))

}