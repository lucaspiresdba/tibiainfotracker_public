package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.character

import br.com.lucaspires.tibiainfotracker.data.extension.defaultSchedulers
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.character.CharacterInfoModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.character.CharacterUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.BaseViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction

internal class CharacterViewModel(private val useCase: CharacterUseCase) :
    BaseViewModel<CharacterInfoModel>() {

    fun getCharacter(name: String) {
        useCase.getCharacter(name)
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                if (it.notFoundData) {
                    action.value = TibiaAction.NotFoundData
                    return@subscribe
                }
                status.value = it
            }, {
                when (it) {
                    is TibiaException.NoConnection -> action.value = TibiaAction.NoInternet
                    else -> action.value = TibiaAction.GenericError
                }
            })
            .toDisposable()
    }

    fun setFavorite(name: String) {
        useCase.setFavorite(name)
            .subscribe {
                val fav = status.value?.isFavorite ?: false
                status.value = status.value?.copy(isFavorite = fav.not())
            }.toDisposable()
    }
}