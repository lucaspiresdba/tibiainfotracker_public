package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.about

import br.com.lucaspires.tibiainfotracker.data.extension.defaultSchedulers
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.support.SupportUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.BaseViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction

internal class AboutViewModel(private val supportUseCase: SupportUseCase) :
    BaseViewModel<List<SupportModel>>() {

    fun getSuppSites() {
        supportUseCase.getSuppSites()
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