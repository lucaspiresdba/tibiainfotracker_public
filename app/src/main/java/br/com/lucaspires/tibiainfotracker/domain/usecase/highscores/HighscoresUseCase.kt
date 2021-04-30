package br.com.lucaspires.tibiainfotracker.domain.usecase.highscores

import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreModel
import io.reactivex.Single

interface HighscoresUseCase {
    fun getHighscores(world: String, rankType: String, vocation: String): Single<HighscoreModel>
}
