package hu.nagya.cryptobroker.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.theme.R
import hu.nagya.cryptobroker.theme.composeable.TopAppBar
import hu.nagya.cryptobroker.features.home.components.CryptoCurrencyList
import hu.nagya.cryptobroker.features.home.preview.CryptoCurrencyPreviewProvider
import hu.nagya.cryptobroker.theme.AppColors
import hu.nagya.cryptobroker.theme.composeable.LoadingHandler
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val currencyList by viewModel.currencyList.collectAsStateWithLifecycle()
    val isLoading by viewModel.fullScreenLoading.collectAsStateWithLifecycle()

    HomeContent(
        currencyList = currencyList.toImmutableList(),
        onCurrencySelected = { currency -> navigateToDetails(currency.id) }
    )

    LoadingHandler(isLoading = isLoading)

    LaunchedEffect(Unit) {
        viewModel.startPolling()
    }
}

@Composable
private fun HomeContent(
    currencyList: ImmutableList<CryptoCurrency>,
    onCurrencySelected: (CryptoCurrency) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primary.primary100alpha20)
    ) {
        TopAppBar(title = stringResource(R.string.home_title))

        CryptoCurrencyList(
            cryptoCurrencyList = currencyList,
            onCurrencySelected = onCurrencySelected
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun HomePreview(
    @PreviewParameter(CryptoCurrencyPreviewProvider::class) currency: CryptoCurrency
) {
    HomeContent(
        currencyList = listOf(currency).toImmutableList(),
        onCurrencySelected = {}
    )
}
