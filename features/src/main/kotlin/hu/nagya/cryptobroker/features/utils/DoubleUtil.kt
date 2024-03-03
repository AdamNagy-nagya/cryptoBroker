package hu.nagya.cryptobroker.features.utils

import kotlin.math.abs

internal fun Double.formatAbbreviated(): String =
    if (this < ONE_THOUSAND) {
        "%.2f".format(this)
    } else {
        val suffix = charArrayOf('K', 'M', 'B', 'T', 'Q')
        val value = abs(this)
        var index = 0
        var formattedValue = value

        while (formattedValue >= ONE_THOUSAND && index < suffix.size - 1) {
            formattedValue /= ONE_THOUSAND
            index++
        }

        String.format("%.2f%s", formattedValue, suffix[index])
    }

private const val ONE_THOUSAND = 1000.0
