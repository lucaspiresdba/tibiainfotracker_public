package br.com.lucaspires.tibiainfotracker.domain.usecase.worlds

import br.com.lucaspires.tibiainfotracker.BaseUnitTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Test

internal class WorldsUseCaseImpTest : BaseUnitTest() {

    @MockK
    lateinit var repository: Repository

    @InjectMockKs
    lateinit var useCase: WorldsUseCaseImp

    @Test
    fun shouldReturnPairOfStrings() {
        every { repository.getCities() } returns Stub.getListOfCities().toSingle()
        every { repository.getWorlds() } returns Stub.getListOfWorlds().toSingle()

        useCase.getWorldsAndCities()
            .test()
            .assertValue(Pair(Stub.getListOfWorlds(), Stub.getListOfCities()))
    }

    @Test
    fun shoudlReturnListOfString(){
        every { repository.getWorlds() } returns Stub.getListOfWorlds().toSingle()

        useCase.getWorlds()
            .test()
            .assertValue(Stub.getListOfWorlds())
    }
}