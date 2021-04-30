package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses

import br.com.lucaspires.tibiainfotracker.BaseViewModelTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.usecase.houses.HousesUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.worlds.WorldsUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.state.HousesState
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.inOrder

internal class HousesViewModelTest : BaseViewModelTest<HousesState, HousesViewModel>() {

    @MockK
    lateinit var useCase: HousesUseCase

    @MockK
    lateinit var worldsUseCase: WorldsUseCase

    @Test
    fun shouldGetHousesData() {
        every { useCase.getHouses(any(), any(), any()) } returns Stub.getHouseModelList().toSingle()

        viewModel.getHouses("any()", "any()", false)

        inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(
                HousesState().copy(houses = Stub.getHouseModelList())
            )
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldGetWorldsAndCitiesData() {
        every { worldsUseCase.getWorldsAndCities() } returns Stub.getPairOfWorldsAndCities()
            .toSingle()

        viewModel.getWorldsAndCities()

        inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(
                HousesState().copy(
                    worlds = Stub.getListOfWorlds(),
                    cities = Stub.getListOfCities()
                )
            )
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun whenGetHousesAndErrorOccursShouldSendActionErros() {
        every { useCase.getHouses(any(), any(), any()) } returns Single.error(Exception())

        viewModel.getHouses("any()", "any()", true)

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun whenGetHousesAndNoConnectionShouldSendActionNoInternet() {
        every {
            useCase.getHouses(
                any(),
                any(),
                any()
            )
        } returns Single.error(TibiaException.NoConnection)

        viewModel.getHouses("any()", "any()", true)

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionErroWhenGenericErrorOccurs() {
        every { worldsUseCase.getWorldsAndCities() } returns Single.error(Exception())

        viewModel.getWorldsAndCities()

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionNoInternetWhenNotConnected() {
        every { worldsUseCase.getWorldsAndCities() } returns Single.error(TibiaException.NoConnection)

        viewModel.getWorldsAndCities()

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }
}