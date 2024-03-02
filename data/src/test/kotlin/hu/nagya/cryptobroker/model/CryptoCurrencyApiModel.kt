package hu.nagya.cryptobroker.model

import hu.nagya.cryptobroker.data.network.response.CryptoCurrencyApiModel

val testCryptoCurrencyApiModel = CryptoCurrencyApiModel(
    id = "1",
    rank = "1",
    symbol = "BTC",
    name = "Bitcoin",
    supply = "100",
    maxSupply = "100",
    marketCapUsd = "1000",
    volumeUsd24Hr = "100",
    priceUsd = "100",
    changePercent24Hr = "0",
    vwap24Hr = "100"
)
