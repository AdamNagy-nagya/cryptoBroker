package hu.nagya.cryptobroker.data.mapper

import hu.nagya.cryptobroker.domain.models.CurrencySymbolType
import hu.nagya.cryptobroker.data.model.testCryptoCurrency
import hu.nagya.cryptobroker.data.model.testCryptoCurrencyApiModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
class CryptoCurrencyApiToDomainMapperTest {

    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with supported symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(result, testCryptoCurrency)
    }

    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with ETH symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "ETH"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "ETH",
                type = CurrencySymbolType.ETH
            )
        )
    }

    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with XRP symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "XRP"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "XRP",
                type = CurrencySymbolType.XRP
            )
        )
    }
    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with USDT symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "USDT"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "USDT",
                type = CurrencySymbolType.USDT
            )
        )
    }

    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with BNB symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "BNB"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "BNB",
                type = CurrencySymbolType.BNB
            )
        )
    }
    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with ADA symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "ADA"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "ADA",
                type = CurrencySymbolType.ADA
            )
        )
    }
    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with AVAX symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "AVAX"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "AVAX",
                type = CurrencySymbolType.AVAX
            )
        )
    }

    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with MATIC symbol type`() {
        // Given
        val cryptoCurrencyApiModel = testCryptoCurrencyApiModel.copy(
            symbol = "MATIC"
        )

        // When
        val result = cryptoCurrencyApiModel.toDomainModel()

        // Then
        assertEquals(
            result,
            testCryptoCurrency.copy(
                symbol = "MATIC",
                type = CurrencySymbolType.MATIC
            )
        )
    }

    @Test
    fun `map CryptoCurrencyApiModel to CryptoCurrency with unknown symbol type`() {
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
