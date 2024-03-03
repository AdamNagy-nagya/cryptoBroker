package hu.nagya.cryptobroker.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.features.preview.CryptoCurrencyPreviewProvider
import hu.nagya.cryptobroker.features.utils.displayChangePercent
import hu.nagya.cryptobroker.features.utils.displayChangePercentColor
import hu.nagya.cryptobroker.features.utils.displayPrice
import hu.nagya.cryptobroker.features.utils.imagePainter
import hu.nagya.cryptobroker.theme.AppColors
import hu.nagya.cryptobroker.theme.AppTypography
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun CryptoCurrencyList(
    cryptoCurrencyList: ImmutableList<CryptoCurrency>,
    onCurrencySelected: (CryptoCurrency) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 24.dp
        )
    ) {
        cryptoCurrencyList.forEach { currency ->
            item(
                key = currency.id,
                contentType = "CryptoCurrencyListItem"
            ) {
                CurrencyItem(
                    currency = currency,
                    onCurrencySelected = onCurrencySelected
                )
            }
        }
    }
}

@Composable
private fun CurrencyItem(
    currency: CryptoCurrency,
    onCurrencySelected: (CryptoCurrency) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.primary.primary0)
            .clickable { onCurrencySelected(currency) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = currency.type.imagePainter(),
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            contentScale = ContentScale.Fit
        )

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currency.name.uppercase(),
                    style = AppTypography.headlineSmall,
                    modifier = Modifier.weight(1f),
                    color = AppColors.text.default
                )

                Text(
                    text = currency.displayPrice,
                    style = AppTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = AppColors.text.default
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currency.symbol,
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    color = AppColors.text.default
                )

                Text(
                    text = currency.displayChangePercent,
                    color = currency.displayChangePercentColor,
                    style = AppTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
@Preview
private fun CryptoCurrencyListPreview(
    @PreviewParameter(CryptoCurrencyPreviewProvider::class) currency: CryptoCurrency
) {
    CryptoCurrencyList(
        cryptoCurrencyList = listOf(currency).toImmutableList(),
        onCurrencySelected = {}
    )
}
