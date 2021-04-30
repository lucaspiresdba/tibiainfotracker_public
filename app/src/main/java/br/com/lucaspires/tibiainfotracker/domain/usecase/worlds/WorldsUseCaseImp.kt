package br.com.lucaspires.tibiainfotracker.domain.usecase.worlds

import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import io.reactivex.Single

internal class WorldsUseCaseImp(private val repository: Repository) : WorldsUseCase {
    override fun getWorlds(): Single<List<String>> = repository.getWorlds()
    override fun getWorldsAndCities(): Single<Pair<List<String>, List<String>>> {
        return Single.zip(getWorlds(), repository.getCities(),
            { world, cities -> Pair(world, cities) })
    }
}