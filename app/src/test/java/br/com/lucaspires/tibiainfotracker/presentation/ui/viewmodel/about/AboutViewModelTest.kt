package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.about

import br.com.lucaspires.tibiainfotracker.BaseViewModelTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.support.SupportUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

internal class AboutViewModelTest : BaseViewModelTest<List<SupportModel>, AboutViewModel>() {

    @MockK
    lateinit var useCase: SupportUseCase

    @Test
    fun shouldGetSuppData() {
        every { useCase.getSuppSites() } returns Stub.getListOfSupporters().toSingle()

        viewModel.getSuppSites()

        Mockito.inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(Stub.getListOfSupporters())
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionErroWhenGenericErrorOccurs() {
        every { useCase.getSuppSites() } returns Single.error(Exception())

        viewModel.getSuppSites()

        Mockito.inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionNoInternetWhenNotConnected() {
        every { useCase.getSuppSites() } returns Single.error(TibiaException.NoConnection)

        viewModel.getSuppSites()

        Mockito.inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }
}