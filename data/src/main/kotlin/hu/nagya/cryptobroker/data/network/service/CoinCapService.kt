package hu.nagya.cryptobroker.data.network.service

import hu.nagya.cryptobroker.data.network.response.CoinApiListResponse
import hu.nagya.cryptobroker.data.network.response.CoinApiSingleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinCapService {

    @GET("assets")
    suspend fun getAll(@Query("limit") limit: Int = 10): CoinApiListResponse

    @GET("assets/{id}")
    suspend fun get(@Path("id") id: String): CoinApiSingleResponse
}
