package hu.nagya.cryptobroker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import hu.nagya.cryptobroker.features.details.currencyDetailsScreen
import hu.nagya.cryptobroker.features.details.navigateToCurrencyDetails
import hu.nagya.cryptobroker.features.home.HOME_NAV_ROUTE
import hu.nagya.cryptobroker.features.home.homeScreen

@Composable
fun CryptoBrokerAppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_NAV_ROUTE
    ) {
        homeScreen(
            navigateToDetails = {
                navController.navigateToCurrencyDetails(it)
            }
        )

        currencyDetailsScreen(
            navigateBack = {
                navController.popBackStack()
            }
        )
    }
}
