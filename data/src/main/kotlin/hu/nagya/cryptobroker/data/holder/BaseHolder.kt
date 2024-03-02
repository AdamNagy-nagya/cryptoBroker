package hu.nagya.cryptobroker.data.holder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.transform

abstract class BaseHolder<T>(defaultValue: T? = null) {

    private val stateFlow = MutableStateFlow(defaultValue)

    open suspend fun setItem(item: T) {
        stateFlow.value = item
    }

    fun getItemFlow() = stateFlow.transform<T?, T> { value ->
        if (value != null) {
            emit(value)
        }
    }

    fun getNullableItemFlow(): Flow<T?> = stateFlow

    suspend fun getFirstItem() = getItemFlow().first()

    suspend fun getFirstItemOrNull() = stateFlow.firstOrNull()
}
