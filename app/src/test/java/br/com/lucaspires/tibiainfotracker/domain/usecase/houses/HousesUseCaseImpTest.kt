package br.com.lucaspires.tibiainfotracker.domain.usecase.houses

import br.com.lucaspires.tibiainfotracker.BaseUnitTest
import br.com.lucaspires.tibiainfotracker.Stub
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import br.com.lucaspires.tibiainfotracker.toSingle
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Test

internal class HousesUseCaseImpTest : BaseUnitTest() {
    @MockK
    lateinit var repository: Repository

    @InjectMockKs
    lateinit var useCase: HousesUseCaseImp

    @Test
    fun shouldReturnHousesModel() {
        every {
            repository.getHouses(
                any(),
                any(),
                any()
            )
        } returns Stub.getHousesResponse().toSingle()

        useCase.getHouses("any()", "any()", true)
            .test()
            .assertValue(Stub.getHouseModelList())
    }

    @Test
    fun shouldReturnHouseDetail() {
        every {
            repository.getHouseDetail(
                any(),
                any()
            )
        } returns Single.just(Stub.getHouseDetailResponse())

        useCase.getHouseDetail("any()", 1)
            .test()
            .assertValue(Stub.getHouseDetailModel())
    }

}