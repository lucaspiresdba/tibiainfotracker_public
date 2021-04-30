package br.com.lucaspires.tibiainfotracker.domain.usecase.news

import br.com.lucaspires.tibiainfotracker.BaseUnitTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Test

internal class NewsUseCaseImpTest : BaseUnitTest() {
    @MockK
    lateinit var repository: Repository

    @InjectMockKs
    lateinit var useCase: NewsUseCaseImp

    @Test
    fun shouldReturnNewsModel() {
        every { repository.getNews() } returns Stub.getNewsResponse().toSingle()

        useCase.getNews()
            .test()
            .assertValue(Stub.getNewsModel())
    }
}