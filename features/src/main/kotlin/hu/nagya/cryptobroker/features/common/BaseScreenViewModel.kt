package hu.nagya.cryptobroker.features.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus

abstract class BaseScreenViewModel : ViewModel() {

    private val fullScreenLoadingCounter = MutableStateFlow(0)

    val fullScreenLoading = fullScreenLoadingCounter.map { it > 0 }.asStateFlow(false)

    protected suspend fun applyLoading(block: suspend () -> Unit) {
        try {
            showFullScreenLoader()

            block()
        } finally {
            hideFullScreenLoader()
        }
    }

    fun <T> Flow<T>.asStateFlow(): StateFlow<T?> = asStateFlow(null)

    fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> =
        stateIn(
            viewModelScope + Dispatchers.Default,
            SharingStarted.WhileSubscribed(),
            initialValue
        )

    protected fun showFullScreenLoader() {
        fullScreenLoadingCounter.value += 1
    }

    protected fun hideFullScreenLoader() {
        fullScreenLoadingCounter.value = (fullScreenLoadingCounter.value - 1).coerceAtLeast(0)
    }
}