package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.highscore

import br.com.lucaspires.tibiainfotracker.BaseViewModelTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.usecase.highscores.HighscoresUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.worlds.WorldsUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.state.HighscoreState
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.inOrder

internal class HighscoreViewModelTest : BaseViewModelTest<HighscoreState, HighscoreViewModel>() {

    @MockK
    lateinit var useCase: HighscoresUseCase

    @MockK
    lateinit var worldsUseCase: WorldsUseCase

    @Test
    fun shouldGetHighscoresData() {
        every { useCase.getHighscores(any(), any(), any()) } returns Stub.getHighscoreModelList()
            .toSingle()

        viewModel.getHighscores("any()", "any()", "any()")

        inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(HighscoreState().copy(highscores = Stub.getHighscoreModelList()))
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldGetWorldsData() {
        every { worldsUseCase.getWorlds() } returns Stub.getListOfWorlds().toSingle()

        viewModel.getWorlds()

        inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(HighscoreState().copy(worldsList = Stub.getListOfWorlds()))
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun whenGetHousesAndErrorOccursShouldSendActionErros() {
        every { useCase.getHighscores(any(), any(), any()) } returns Single.error(Exception())

        viewModel.getHighscores("any()", "any()", "any()")

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun whenGetHousesAndNoConnectionShouldSendActionNoInternet() {
        every {
            useCase.getHighscores(
                any(),
                any(),
                any()
            )
        } returns Single.error(TibiaException.NoConnection)

        viewModel.getHighscores("any()", "any()", "any()")

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionErroWhenGenericErrorOccurs() {
        every { worldsUseCase.getWorlds() } returns Single.error(Exception())

        viewModel.getWorlds()

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionNoInternetWhenNotConnected() {
        every { worldsUseCase.getWorlds() } returns Single.error(TibiaException.NoConnection)

        viewModel.getWorlds()

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }

}