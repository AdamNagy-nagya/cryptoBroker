package hu.nagya.cryptobroker.features.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.repeatWhen(predicate: suspend FlowCollector<T>.(attempt: Long) -> Boolean): Flow<T> =
    flow {
        collect { value -> emit(value) }

        var attempt = 0L

        while (predicate(attempt)) {
            collect { value -> emit(value) }
            attempt++
        }
    }
