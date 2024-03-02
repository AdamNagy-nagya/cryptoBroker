package hu.nagya.cryptobroker.features.home

import androidx.lifecycle.viewModelScope
import hu.nagya.cryptobroker.domain.action.CryptoCurrencyAction
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.domain.store.CryptoCurrencyStore
import hu.nagya.cryptobroker.features.common.BaseScreenViewModel
import hu.nagya.cryptobroker.features.common.repeatWhen
import hu.nagya.cryptobroker.features.common.retry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

internal class HomeViewModel(
    private val cryptoCurrencyAction: CryptoCurrencyAction,
    cryptoCurrencyStore: CryptoCurrencyStore
) : BaseScreenViewModel() {

    private val pollingEnabledFlow = MutableStateFlow(false)

    val currencyList = cryptoCurrencyStore.getAll()
        .asStateFlow(emptyList())

    fun startPolling() {
        pollingEnabledFlow.value = true
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            applyLoading {
                cryptoCurrencyAction.refreshAll()
            }
        }

        viewModelScope.launch(Dispatchers.Default) {
            supervisorScope {
                launch {
                    pollingEnabledFlow
                        .flatMapLatest { enabled ->
                            if (enabled) {
                                flowOf(Unit)
                                    .repeatWhen(retry(1.minutes.inWholeMilliseconds))
                            } else {
                                flowOf()
                            }
                        }
                        .collect {
                            cryptoCurrencyAction.refreshAll()
                        }
                }
            }
        }
    }
}
