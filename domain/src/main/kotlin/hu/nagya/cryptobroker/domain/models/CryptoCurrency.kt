package hu.nagya.cryptobroker.domain.models

data class CryptoCurrency(
    val id: String,
    val symbol :String,
    val name: String,
    val price: Double,
    val changePercent: Double,
    val marketCap: Double,
    val exchangeVolume: Double,
    val supply: Double,
    val type: CurrencySymbolType
)
