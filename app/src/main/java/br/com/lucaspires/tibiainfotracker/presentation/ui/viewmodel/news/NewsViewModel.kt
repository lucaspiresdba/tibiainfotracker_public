package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.news

import br.com.lucaspires.tibiainfotracker.data.extension.defaultSchedulers
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.news.NewsUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.BaseViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction.NoInternet

internal class NewsViewModel(private val useCase: NewsUseCase) : BaseViewModel<List<NewsModel>>() {

    fun getNews() {
        useCase.getNews()
            .defaultSchedulers()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                status.value = it
            },
                {
                    when (it) {
                        is TibiaException.NoConnection -> action.value = NoInternet
                        else -> action.value = TibiaAction.GenericError
                    }
                })
            .toDisposable()
    }
}