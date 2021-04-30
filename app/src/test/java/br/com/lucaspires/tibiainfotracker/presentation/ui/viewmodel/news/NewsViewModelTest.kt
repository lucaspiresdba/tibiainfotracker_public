package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.news

import br.com.lucaspires.tibiainfotracker.BaseViewModelTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.news.NewsUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

internal class NewsViewModelTest : BaseViewModelTest<List<NewsModel>, NewsViewModel>() {

    @MockK
    lateinit var useCase: NewsUseCase

    @Test
    fun shouldGetCharacterData() {
        every { useCase.getNews() } returns Stub.getNewsModel().toSingle()

        viewModel.getNews()

        Mockito.inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(Stub.getNewsModel())
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionErroWhenGenericErrorOccurs() {
        every { useCase.getNews() } returns Single.error(Exception())

        viewModel.getNews()

        Mockito.inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionNoInternetWhenNotConnected() {
        every { useCase.getNews() } returns Single.error(TibiaException.NoConnection)

        viewModel.getNews()

        Mockito.inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }
}