package hu.nagya.cryptobroker.data.network.response

import com.squareup.moshi.Json

data class CoinApiSingleResponse(
    @Json(name = "data") val data: CryptoCurrencyApiModel,
    @Json(name = "timestamp") val timestamp: Long
)

data class CoinApiListResponse(
    @Json(name = "data") val data: List<CryptoCurrencyApiModel>,
    @Json(name = "timestamp") val timestamp: Long
)
