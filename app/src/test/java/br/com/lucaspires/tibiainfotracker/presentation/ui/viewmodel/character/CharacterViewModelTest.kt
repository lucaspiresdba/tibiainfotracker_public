package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.character

import br.com.lucaspires.tibiainfotracker.BaseViewModelTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import br.com.lucaspires.tibiainfotracker.domain.model.character.CharacterInfoModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.character.CharacterUseCase
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.inOrder

internal class CharacterViewModelTest :
    BaseViewModelTest<CharacterInfoModel, CharacterViewModel>() {

    @MockK
    lateinit var useCase: CharacterUseCase

    @Test
    fun shouldGetCharacterData() {
        every { useCase.getCharacter(any()) } returns Stub.getCharacterModel().toSingle()

        viewModel.getCharacter("any()")

        inOrder(observerLoading, observerStatus).run {
            verify(observerLoading).onChanged(true)
            verify(observerStatus).onChanged(Stub.getCharacterModel())
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionEmptyWhenCharacterNotFound() {
        every { useCase.getCharacter(any()) } returns Stub.getCharacterModel()
            .copy(notFoundData = true).toSingle()

        viewModel.getCharacter("any()")

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NotFoundData)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionErroWhenGenericErrorOccurs() {
        every { useCase.getCharacter(any()) } returns Single.error(Exception())

        viewModel.getCharacter("any()")

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.GenericError)
            verify(observerLoading).onChanged(false)
        }
    }

    @Test
    fun shouldSendActionNoInternetWhenNotConnected() {
        every { useCase.getCharacter(any()) } returns Single.error(TibiaException.NoConnection)

        viewModel.getCharacter("any()")

        inOrder(observerLoading, observerAction).run {
            verify(observerLoading).onChanged(true)
            verify(observerAction).onChanged(TibiaAction.NoInternet)
            verify(observerLoading).onChanged(false)
        }
    }
}