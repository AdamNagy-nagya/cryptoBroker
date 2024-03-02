package hu.nagya.cryptobroker.features.common

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector

fun retry(
    delayMillis: Long,
    limitCount: Long = Long.MAX_VALUE
): suspend FlowCollector<*>.(attempt: Long) -> Boolean = { attempt ->
    if (attempt < limitCount) {
        delay(delayMillis)
        true
    } else {
        false
    }
}
