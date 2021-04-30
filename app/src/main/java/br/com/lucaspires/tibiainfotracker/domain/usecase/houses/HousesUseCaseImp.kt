package br.com.lucaspires.tibiainfotracker.domain.usecase.houses

import br.com.lucaspires.tibiainfotracker.domain.extension.toHouseModel
import br.com.lucaspires.tibiainfotracker.domain.extension.toModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseModel
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import io.reactivex.Single

internal class HousesUseCaseImp(private val repository: Repository) : HousesUseCase {
    override fun getHouses(
        world: String,
        town: String,
        isGuildHouse: Boolean
    ): Single<List<HouseModel>> =
        repository.getHouses(world, town, isGuildHouse)
            .map { it.houses?.toHouseModel() }

    override fun getHouseDetail(world: String, houseId: Int): Single<HouseDetailModel> =
        repository.getHouseDetail(world, houseId)
            .map { it.house?.toModel() }
}