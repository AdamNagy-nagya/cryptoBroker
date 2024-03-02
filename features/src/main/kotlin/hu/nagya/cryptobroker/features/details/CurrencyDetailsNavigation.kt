package hu.nagya.cryptobroker.features.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val CURRENCY_DETAILS_NAV_ROUTE = "currencyDetails"
private const val CURRENCY_ID_ARG = "currencyId"

fun NavController.navigateToCurrencyDetails(currencyId: String) {
    this.navigate("$CURRENCY_DETAILS_NAV_ROUTE/$currencyId")
}

fun NavGraphBuilder.currencyDetailsScreen(navigateBack: () -> Unit) {
    composable(
        route = "$CURRENCY_DETAILS_NAV_ROUTE/{$CURRENCY_ID_ARG}",
        arguments = listOf(
            navArgument(CURRENCY_ID_ARG) { type = NavType.StringType }
        )
    ) {
        val currencyId = it.arguments?.getString(CURRENCY_ID_ARG)

        CurrencyDetailsScreen(
            currencyId = currencyId,
            navigateBack = navigateBack
        )
    }
}
