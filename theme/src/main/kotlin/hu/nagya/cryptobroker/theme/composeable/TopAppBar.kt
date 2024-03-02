package hu.nagya.cryptobroker.theme.composeable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.nagya.cryptobroker.theme.R
import hu.nagya.cryptobroker.theme.AppColors
import hu.nagya.cryptobroker.theme.AppTypography

@Composable
fun TopAppBar(
    title: String,
    navigateBack: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.primary.primary100alpha30)
            .padding(getTopBarPadding(navigateBack != null)),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (navigateBack != null) {
            IconButton(onClick = navigateBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = stringResource(
                        id = R.string.common_back_content_desc
                    ),
                    tint = AppColors.text.default
                )
            }
        }

        Text(
            text = title.uppercase(),
            style = AppTypography.headlineLarge,
            color = AppColors.text.default
        )
    }
}

private fun getTopBarPadding(backVisible: Boolean): PaddingValues = if (backVisible) {
    PaddingValues(horizontal = 8.dp, vertical = 12.dp)
} else {
    PaddingValues(horizontal = 24.dp, vertical = 16.dp)
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun TopAppBarPreview() {
    TopAppBar(title = "TopAppBar")
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun TopAppBarWithBackPreview() {
    TopAppBar(
        title = "TopAppBar",
        navigateBack = { }
    )
}
