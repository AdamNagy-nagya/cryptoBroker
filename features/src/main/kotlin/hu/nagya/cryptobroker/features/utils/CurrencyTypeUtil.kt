package hu.nagya.cryptobroker.features.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import hu.nagya.cryptobroker.domain.models.CurrencySymbolType
import hu.nagya.cryptobroker.theme.R

@Composable
internal fun CurrencySymbolType.imagePainter() = painterResource(
    when (this) {
        CurrencySymbolType.BTC -> R.drawable.illustration_btc
        CurrencySymbolType.ETH -> R.drawable.illustration_eth
        CurrencySymbolType.XRP -> R.drawable.illustration_xrp
        CurrencySymbolType.USDT -> R.drawable.illustration_usdt
        CurrencySymbolType.BNB -> R.drawable.illustration_bnb
        CurrencySymbolType.ADA -> R.drawable.illustration_ada
        CurrencySymbolType.AVAX -> R.drawable.illustration_avax
        CurrencySymbolType.MATIC -> R.drawable.illustration_matic
        CurrencySymbolType.UNKNOWN -> R.drawable.illustration_unknown
    }
)
