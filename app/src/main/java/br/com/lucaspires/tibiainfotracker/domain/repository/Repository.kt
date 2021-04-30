package br.com.lucaspires.tibiainfotracker.domain.repository

import androidx.lifecycle.LiveData
import br.com.lucaspires.tibiainfotracker.data.model.character.CharacterResponse
import br.com.lucaspires.tibiainfotracker.data.model.highscore.HighscoresResponse
import br.com.lucaspires.tibiainfotracker.data.model.houses.HouseDetailsResponse
import br.com.lucaspires.tibiainfotracker.data.model.houses.HousesResponse
import br.com.lucaspires.tibiainfotracker.data.model.news.NewsResponse
import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {
    fun getCharacter(name: String): Single<CharacterResponse>
    fun getNews(): Single<NewsResponse>
    fun getHouses(world: String, town: String, isGuildHouse: Boolean): Single<HousesResponse>
    fun getHouseDetail(world: String, houseId: Int): Single<HouseDetailsResponse>
    fun getHighscores(world: String, rankType: String, vocation: String): Single<HighscoresResponse>
    fun getWorlds(): Single<List<String>>
    fun getCities(): Single<List<String>>
    fun getAllFavorites(): LiveData<List<String>>
    fun setFavorite(name: String): Completable
    fun isFavoritedChar(name: String): Single<Boolean>
    fun getSuppSites(): Single<List<SupportModel>>
}
