package br.com.lucaspires.tibiainfotracker.domain.usecase.worlds

import io.reactivex.Single

interface WorldsUseCase {
    fun getWorlds(): Single<List<String>>
    fun getWorldsAndCities(): Single<Pair<List<String>, List<String>>>
}
