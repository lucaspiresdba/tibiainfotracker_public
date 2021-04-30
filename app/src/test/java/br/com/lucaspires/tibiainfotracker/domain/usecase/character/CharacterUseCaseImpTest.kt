package br.com.lucaspires.tibiainfotracker.domain.usecase.character

import br.com.lucaspires.tibiainfotracker.BaseUnitTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Test

internal class CharacterUseCaseImpTest : BaseUnitTest() {

    @MockK
    lateinit var repository: Repository

    @InjectMockKs
    lateinit var useCase: CharacterUseCaseImp

    @Test
    fun shoudlReturnModel() {
        every { repository.getCharacter("Teste+Teste+Teste") } returns Stub.getCharacterResponse()
            .toSingle()
        every { repository.isFavoritedChar("teste teste teste") } returns true.toSingle()

        useCase.getCharacter("Teste Teste Teste")
            .test()
            .assertResult(Stub.getCharacterModel())
    }
}