package hu.nagya.cryptobroker.data.mapper

import hu.nagya.cryptobroker.data.network.response.CryptoCurrencyApiModel
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.domain.models.CurrencySymbolType

fun CryptoCurrencyApiModel.toDomainModel()= CryptoCurrency(
    id = id,
    symbol = symbol,
    name = name,
    price = priceUsd.toDouble(),
    changePercent = changePercent24Hr.toDouble(),
    marketCap = marketCapUsd.toDouble(),
    exchangeVolume = volumeUsd24Hr.toDouble(),
    supply = supply.toDouble(),
    type = symbol.toCurrencySymbolType()
)

private fun String.toCurrencySymbolType() = when(this){
    "BTC" -> CurrencySymbolType.BTC
    "ETH" -> CurrencySymbolType.ETH
    "XRP" -> CurrencySymbolType.XRP
    "BNB" -> CurrencySymbolType.BNB
    "USDT" -> CurrencySymbolType.USDT
    "ADA" -> CurrencySymbolType.ADA
    "AVAX" -> CurrencySymbolType.AVAX
    "MATIC" -> CurrencySymbolType.MATIC
    else -> CurrencySymbolType.UNKNOWN
}
