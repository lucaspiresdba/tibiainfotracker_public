package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.state

import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseModel

data class HousesState(
    val worlds: List<String> = emptyList(),
    val houses: List<HouseModel> = emptyList(),
    val cities: List<String> = emptyList(),
)
