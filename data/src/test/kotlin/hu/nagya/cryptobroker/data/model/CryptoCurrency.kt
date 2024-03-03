package hu.nagya.cryptobroker.data.model

import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.domain.models.CurrencySymbolType

val testCryptoCurrency = CryptoCurrency(
    id = "1",
    symbol = "BTC",
    name = "Bitcoin",
    price = 100.0,
    changePercent = 0.0,
    marketCap = 1000.0,
    exchangeVolume = 100.0,
    supply = 100.0,
    type = CurrencySymbolType.BTC
)
