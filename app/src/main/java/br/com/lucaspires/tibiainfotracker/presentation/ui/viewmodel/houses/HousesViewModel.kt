package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses

import androidx.lifecycle.MutableLiveData
import br.com.lucaspires.tibiainfotracker.data.extension.defaultSchedulers
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.usecase.houses.HousesUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.worlds.WorldsUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.BaseViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.state.HousesState

internal class HousesViewModel(
    private val worldsUseCase: WorldsUseCase,
    private val housesUseCase: HousesUseCase
) : BaseViewModel<HousesState>() {

    override val status = MutableLiveData(HousesState())

    fun getWorldsAndCities() {
        worldsUseCase.getWorldsAndCities()
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({ pair ->
                status.value = status.value?.copy(worlds = pair.first, cities = pair.second)
            }, {
                when (it) {
                    is TibiaException.NoConnection -> action.value = TibiaAction.NoInternet
                    else -> action.value = TibiaAction.GenericError
                }
            })
            .toDisposable()
    }

    fun getHouses(world: String, town: String, isGuildHouse: Boolean) {
        housesUseCase.getHouses(world = world, town = town, isGuildHouse)
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                status.value = status.value?.copy(houses = it)
            },
                {
                    when (it) {
                        is TibiaException.NoConnection -> action.value = TibiaAction.NoInternet
                        else -> action.value = TibiaAction.GenericError
                    }
                })
            .toDisposable()
    }
}