package hu.nagya.cryptobroker.features.details

import androidx.lifecycle.viewModelScope
import hu.nagya.cryptobroker.domain.action.CryptoCurrencyAction
import hu.nagya.cryptobroker.domain.store.CryptoCurrencyStore
import hu.nagya.cryptobroker.features.common.BaseScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

internal class CurrencyDetailsViewModel(
    private val cryptoCurrencyAction: CryptoCurrencyAction,
    cryptoCurrencyStore: CryptoCurrencyStore
) : BaseScreenViewModel() {

    private val currencyIdFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    val currency = currencyIdFlow
        .filterNotNull()
        .flatMapLatest { id -> cryptoCurrencyStore.get(id) }
        .asStateFlow()


    fun init(id: String) {
        currencyIdFlow.value = id

        viewModelScope.launch(Dispatchers.Default) {
            applyLoading {
                cryptoCurrencyAction.refresh(id)
            }
        }
    }
}
