package hu.nagya.cryptobroker.data.store

import hu.nagya.cryptobroker.data.holder.CryptoCurrencyHolder
import hu.nagya.cryptobroker.data.model.testCryptoCurrency
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptoCurrencyStoreImplTest {

    @MockK
    lateinit var currencyHolder: CryptoCurrencyHolder

    private fun createStore() = CryptoCurrencyStoreImpl(currencyHolder)

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery { currencyHolder.getItemFlow() } returns flowOf(listOf(testCryptoCurrency))
    }

    @Test
    fun `getAll returns list of crypto currencies`() = runTest {
        // Given
        val store = createStore()

        // When
        val actualCurrencies = store.getAll().first()

        // Then
        assertEquals(listOf(testCryptoCurrency), actualCurrencies)
    }

    @Test
    fun `get returns crypto currency by id`() = runTest {
        // Given
        val store = createStore()
        val id = testCryptoCurrency.id

        // When
        val actualCurrency = store.get(id).first()

        // Then
        assertEquals(testCryptoCurrency, actualCurrency)
    }
}
