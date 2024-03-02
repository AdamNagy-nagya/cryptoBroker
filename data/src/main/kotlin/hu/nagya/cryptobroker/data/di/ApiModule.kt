package hu.nagya.cryptobroker.data.di

import hu.nagya.cryptobroker.data.network.service.CoinCapService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<CoinCapService> { get<Retrofit>().create(CoinCapService::class.java) }
}

const val BASE_URL = "https://api.coincap.io/v2/"
