package hu.nagya.cryptobroker.mapper

import hu.nagya.cryptobroker.data.mapper.toDomainModel
import hu.nagya.cryptobroker.domain.models.CurrencySymbolType
import hu.nagya.cryptobroker.model.testCryptoCurrency
import hu.nagya.cryptobroker.model.testCryptoCurrencyApiModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CryptoCurrencyApiToDomainMapperTest {

    @Test
    fun `Map CryptoCurrencyApiModel to CryptoCurrency with supported symbol type`() = runTest {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(result, testCryptoCurrency)
    }

    @Test
    fun `Map CryptoCurrencyApiModel to CryptoCurrency with unknown symbol type`() = runTest {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "not_supported"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "not_supported",
                type = CurrencySymbolType.UNKNOWN
            )
        )
    }
}
