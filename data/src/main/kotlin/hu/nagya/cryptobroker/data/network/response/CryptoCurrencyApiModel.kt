package hu.nagya.cryptobroker.data.network.response

import com.squareup.moshi.Json

data class CryptoCurrencyApiModel(
    @Json(name = "id") val id: String,
    @Json(name = "rank") val rank: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "name") val name: String,
    @Json(name = "supply") val supply: String,
    @Json(name = "maxSupply") val maxSupply: String,
    @Json(name = "marketCapUsd") val marketCapUsd: String,
    @Json(name = "volumeUsd24Hr") val volumeUsd24Hr: String,
    @Json(name = "priceUsd") val priceUsd: String,
    @Json(name = "changePercent24Hr") val changePercent24Hr: String,
    @Json(name = "vwap24Hr") val vwap24Hr: String
)