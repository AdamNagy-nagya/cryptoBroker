package hu.nagya.cryptobroker.data.di

import hu.nagya.cryptobroker.data.action.CryptoCryptoCurrencyActionImpl
import hu.nagya.cryptobroker.data.holder.CryptoCurrencyHolder
import hu.nagya.cryptobroker.data.store.CryptoCurrencyStoreImpl
import hu.nagya.cryptobroker.domain.action.CryptoCurrencyAction
import hu.nagya.cryptobroker.domain.store.CryptoCurrencyStore
import org.koin.dsl.module

val dataModule = module {
    factory<CryptoCurrencyStore> { CryptoCurrencyStoreImpl(get()) }

    factory<CryptoCurrencyAction> { CryptoCryptoCurrencyActionImpl(get(), get()) }

    single<CryptoCurrencyHolder> { CryptoCurrencyHolder() }
}

