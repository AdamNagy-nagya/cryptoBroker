package hu.nagya.cryptobroker.features.preview

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.domain.models.CurrencySymbolType
import java.util.UUID
import kotlin.random.Random

internal class CryptoCurrencyPreviewProvider : CollectionPreviewParameterProvider<CryptoCurrency>(
    listOf(generateCryptoCurrency())
)

private fun generateCryptoCurrency() = CryptoCurrency(
    id = UUID.randomUUID().toString(),
    name = listOf("Bitcoin", "Ethereum", "Ripple", "Litecoin", "Cardano").shuffled().first(),
    symbol = listOf("BTC", "ETH", "XRP", "LTC", "ADA").shuffled().first(),
    price = Random.nextDouble(1000.0),
    changePercent = Random.nextDouble(100.0),
    marketCap = Random.nextDouble(1000.0),
    exchangeVolume = Random.nextDouble(1000.0),
    supply = Random.nextDouble(1000.0),
    type = CurrencySymbolType.entries.shuffled().first()
)
