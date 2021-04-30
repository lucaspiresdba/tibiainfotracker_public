package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.highscore

import androidx.lifecycle.MutableLiveData
import br.com.lucaspires.tibiainfotracker.data.extension.defaultSchedulers
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.usecase.highscores.HighscoresUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.worlds.WorldsUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.BaseViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.state.HighscoreState

internal class HighscoreViewModel(
    private val highscoresUseCase: HighscoresUseCase,
    private val worldsUseCase: WorldsUseCase,
) : BaseViewModel<HighscoreState>() {

    override val status = MutableLiveData(HighscoreState())

    fun getHighscores(world: String, rankType: String, vocation: String) {
        highscoresUseCase.getHighscores(world, rankType, vocation)
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                status.value = status.value?.copy(highscores = it)
            }, {
                when (it) {
                    is TibiaException.NoConnection -> action.value = TibiaAction.NoInternet
                    else -> action.value = TibiaAction.GenericError
                }
            })
            .toDisposable()
    }

    fun getWorlds() {
        worldsUseCase.getWorlds()
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({ worlds ->
                status.value = status.value?.copy(worldsList = worlds)
            }, {
                when (it) {
                    is TibiaException.NoConnection -> action.value = TibiaAction.NoInternet
                    else -> action.value = TibiaAction.GenericError
                }
                it.printStackTrace()
            })
            .toDisposable()
    }
}