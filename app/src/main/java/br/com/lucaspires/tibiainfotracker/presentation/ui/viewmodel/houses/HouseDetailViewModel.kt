package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses

import br.com.lucaspires.tibiainfotracker.data.extension.defaultSchedulers
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.houses.HousesUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.BaseViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction

internal class HouseDetailViewModel(
    private val housesUseCase: HousesUseCase
) : BaseViewModel<HouseDetailModel>() {

    fun getHouseDetails(world: String, houseId: Int) {
        housesUseCase.getHouseDetail(world, houseId)
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe(
                {
                    status.value = it
                },
                {
                    when (it) {
                        is TibiaException.NoConnection -> action.value = TibiaAction.NoInternet
                        else -> action.value = TibiaAction.GenericError
                    }
                }).toDisposable()
    }
}