package hu.nagya.cryptobroker.features.utils

import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.theme.AppColors


internal val CryptoCurrency.displayPrice get() = USD_CURRENCY_SYMBOL + price.formatAbbreviated()

internal val CryptoCurrency.displayMarketCap get() = USD_CURRENCY_SYMBOL + marketCap.formatAbbreviated()

internal val CryptoCurrency.displayExchangeVolume get() =
    USD_CURRENCY_SYMBOL + exchangeVolume.formatAbbreviated()

internal val CryptoCurrency.displaySupply get() = supply.formatAbbreviated()

internal val CryptoCurrency.displayChangePercent get() = "%.2f%%".format(changePercent)

internal val CryptoCurrency.displayChangePercentColor get() =
    if (changePercent > 0) {
        AppColors.state.success
    } else {
        AppColors.state.error
    }

private const val USD_CURRENCY_SYMBOL = "$"
