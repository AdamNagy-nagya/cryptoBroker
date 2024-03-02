package hu.nagya.cryptobroker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import hu.nagya.cryptobroker.data.di.apiModule
import hu.nagya.cryptobroker.data.di.dataModule
import hu.nagya.cryptobroker.features.di.featuresModule
import hu.nagya.cryptobroker.ui.CryptoBrokerAppNavigation
import hu.nagya.cryptobroker.theme.AppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(dataModule, featuresModule, apiModule)
        }
        setContent {
            AppTheme {
                CryptoBrokerAppNavigation(navController = rememberNavController())
            }
        }
    }
}
