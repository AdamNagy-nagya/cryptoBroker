package hu.nagya.cryptobroker.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.theme.R
import hu.nagya.cryptobroker.features.preview.CryptoCurrencyPreviewProvider
import hu.nagya.cryptobroker.features.utils.displayChangePercent
import hu.nagya.cryptobroker.features.utils.displayChangePercentColor
import hu.nagya.cryptobroker.features.utils.displayExchangeVolume
import hu.nagya.cryptobroker.features.utils.displayMarketCap
import hu.nagya.cryptobroker.features.utils.displayPrice
import hu.nagya.cryptobroker.features.utils.displaySupply
import hu.nagya.cryptobroker.theme.AppColors
import hu.nagya.cryptobroker.theme.AppTypography
import hu.nagya.cryptobroker.theme.composeable.LoadingHandler
import hu.nagya.cryptobroker.theme.composeable.TopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CurrencyDetailsScreen(
    currencyId: String?,
    navigateBack: () -> Unit,
    viewModel: CurrencyDetailsViewModel = koinViewModel()
) {
    val currency by viewModel.currency.collectAsStateWithLifecycle()
    val isLoading by viewModel.fullScreenLoading.collectAsStateWithLifecycle()

    CurrencyDetailsContent(
        cryptoCurrency = currency,
        back = navigateBack
    )

    LoadingHandler(isLoading = isLoading)

    LaunchedEffect(currencyId != null) {
        currencyId?.let { viewModel.init(it) }
    }
}

@Composable
private fun CurrencyDetailsContent(
    cryptoCurrency: CryptoCurrency?,
    back: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primary.primary100alpha20)
            .navigationBarsPadding()

    ) {
        TopAppBar(
            title = cryptoCurrency?.name.orEmpty().uppercase(),
            navigateBack = back
        )

        Box(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
                .fillMaxSize()
        ) {
            cryptoCurrency?.let {
                CurrencyData(it)
            }
        }
    }
}

@Composable
private fun CurrencyData(
    cryptoCurrency: CryptoCurrency
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppColors.primary.primary0,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp)

    ) {
        DataRow(
            label = stringResource(R.string.details_price),
            value = cryptoCurrency.displayPrice
        )

        DataRow(
            label = stringResource(R.string.details_change),
            value = cryptoCurrency.displayChangePercent,
            valueColor = cryptoCurrency.displayChangePercentColor
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            color = AppColors.primary.primary90
        )

        DataRow(
            label = stringResource(R.string.details_market_cap),
            value = cryptoCurrency.displayMarketCap
        )

        DataRow(
            label = stringResource(R.string.details_volume),
            value = cryptoCurrency.displayExchangeVolume
        )

        DataRow(
            label = stringResource(R.string.details_supply),
            value = cryptoCurrency.displaySupply
        )
    }
}

@Composable
private fun DataRow(
    label: String,
    value: String,
    valueColor: Color = AppColors.text.default
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = AppTypography.bodyMedium,
            color = AppColors.text.default,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = AppTypography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = valueColor
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun CurrencyDetailsPreview(
    @PreviewParameter(CryptoCurrencyPreviewProvider::class) cryptoCurrency: CryptoCurrency
) {
    CurrencyDetailsContent(
        cryptoCurrency = cryptoCurrency,
        back = {}
    )
}
