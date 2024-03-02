package hu.nagya.cryptobroker.features.di

import hu.nagya.cryptobroker.features.details.CurrencyDetailsViewModel
import hu.nagya.cryptobroker.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CurrencyDetailsViewModel(get(), get()) }
}
