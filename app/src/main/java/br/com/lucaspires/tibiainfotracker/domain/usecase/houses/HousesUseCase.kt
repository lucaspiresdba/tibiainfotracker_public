package br.com.lucaspires.tibiainfotracker.domain.usecase.houses

import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseModel
import io.reactivex.Single

interface HousesUseCase {
    fun getHouses(world: String, town: String, isGuildHouse: Boolean): Single<List<HouseModel>>
    fun getHouseDetail(world: String, houseId: Int): Single<HouseDetailModel>
}
