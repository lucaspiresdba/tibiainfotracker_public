package br.com.lucaspires.tibiainfotracker.domain.usecase.highscores

import android.annotation.SuppressLint
import br.com.lucaspires.tibiainfotracker.domain.extension.toHighscoreModel
import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreModel
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import io.reactivex.Single

@SuppressLint("DefaultLocale")
internal class HighscoresUseCaseImp(private val repository: Repository) : HighscoresUseCase {

    override fun getHighscores(
        world: String,
        rankType: String,
        vocation: String
    ): Single<HighscoreModel> =
        repository.getHighscores(
            world.toLowerCase(),
            rankType.toLowerCase(),
            vocation.toLowerCase()
        )
            .map { it.highscores?.toHighscoreModel() }
}