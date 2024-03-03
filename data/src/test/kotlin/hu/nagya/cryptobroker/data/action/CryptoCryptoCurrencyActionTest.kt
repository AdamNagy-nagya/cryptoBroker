package hu.nagya.cryptobroker.data.action

import hu.nagya.cryptobroker.data.holder.CryptoCurrencyHolder
import hu.nagya.cryptobroker.data.mapper.toDomainModel
import hu.nagya.cryptobroker.data.network.response.CoinApiListResponse
import hu.nagya.cryptobroker.data.network.response.CoinApiSingleResponse
import hu.nagya.cryptobroker.data.network.service.CoinCapService
import hu.nagya.cryptobroker.data.model.testCryptoCurrency
import hu.nagya.cryptobroker.data.model.testCryptoCurrencyApiModel
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CryptoCryptoCurrencyActionImplTest {

    @MockK
    private lateinit var coinCapService: CoinCapService

    @MockK
    private lateinit var currencyHolder: CryptoCurrencyHolder

    private fun createAction() =
        CryptoCryptoCurrencyActionImpl(currencyHolder, coinCapService)

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery { currencyHolder.setItem(listOf(testCryptoCurrency)) } just Runs
    }

    @Test
    fun `refreshAll should update cryptoCurrencyHolder with domain models from CoinCapService`() =
        runTest {
            //Given
            val apiModelList = listOf(testCryptoCurrencyApiModel)
            coEvery { coinCapService.getAll() } returns CoinApiListResponse(apiModelList, 123456789)
            coEvery { currencyHolder.getFirstItemOrNull() } returns listOf(testCryptoCurrency)
            val action = createAction()

            // When
            action.refreshAll()

            // Then
            val expectedDomainModels = apiModelList.map { it.toDomainModel() }
            coVerify { currencyHolder.setItem(expectedDomainModels) }
        }

    @Test
    fun `refresh should update cryptoCurrencyHolder with refreshed domain model`() = runTest {
        //Given
        val action = createAction()
        val apiModel = testCryptoCurrencyApiModel
        val id = apiModel.id
        coEvery { coinCapService.get(id) } returns CoinApiSingleResponse(apiModel, 123456789)
        coEvery { currencyHolder.getFirstItemOrNull() } returns listOf(testCryptoCurrency)

        // When
        action.refresh(id)

        // Then
        val expectedDomainModel = apiModel.toDomainModel()
        coVerify {
            currencyHolder.setItem(match {
                it.firstOrNull { item -> item.id == expectedDomainModel.id } == expectedDomainModel
            })
        }
    }
}
