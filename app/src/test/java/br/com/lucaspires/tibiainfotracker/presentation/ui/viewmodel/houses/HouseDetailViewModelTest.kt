package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses

import br.com.lucaspires.tibiainfotracker.BaseViewModelTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.houses.HousesUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

internal class HouseDetailViewModelTest :
    BaseViewModelTest<HouseDetailModel, HouseDetailViewModel>() {

    @MockK
    lateinit var useCase: HousesUseCase

    @Test
    fun shouldGetHouseDetailData() {
        every { useCase.getHouseDetail(any(), any()) } returns Stub.getHouseDetailModel().toSingle()

        viewModel.getHouseDetails("any()", 1)

        Mockito.inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(Stub.getHouseDetailModel())
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionErroWhenGenericErrorOccurs() {
        every { useCase.getHouseDetail(any(), any()) } returns Single.error(Exception())

        viewModel.getHouseDetails("any()", 1)

        Mockito.inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionNoInternetWhenNotConnected() {
        every {
            useCase.getHouseDetail(
                any(),
                any()
            )
        } returns Single.error(TibiaException.NoConnection)

        viewModel.getHouseDetails("any()", 1)

        Mockito.inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }
}