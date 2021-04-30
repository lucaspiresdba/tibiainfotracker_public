package br.com.lucaspires.tibiainfotracker.domain.usecase.highscores

import br.com.lucaspires.tibiainfotracker.BaseUnitTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Test

internal class HighscoresUseCaseImpTest : BaseUnitTest() {

    @MockK
    lateinit var repository: Repository

    @InjectMockKs
    lateinit var useCase: HighscoresUseCaseImp

    @Test
    fun shoudlReturnModel() {
        every {
            repository.getHighscores(
                any(),
                any(),
                any()
            )
        } returns Stub.getHighscoreResponse().toSingle()

        useCase.getHighscores("any()", "any()", "any()")
            .test()
            .assertValue(Stub.getHighscoreModelList())
    }
}