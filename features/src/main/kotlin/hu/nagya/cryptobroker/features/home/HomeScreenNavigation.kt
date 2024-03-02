package hu.nagya.cryptobroker.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import hu.nagya.cryptobroker.features.details.CurrencyDetailsScreen

const val HOME_NAV_ROUTE = "home"

fun NavController.navigateToHomeScreen() {
    this.navigate(HOME_NAV_ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.homeScreen(navigateToDetails: (currencyId: String) -> Unit) {
    composable(HOME_NAV_ROUTE) {
        HomeScreen(navigateToDetails = navigateToDetails)
    }
}