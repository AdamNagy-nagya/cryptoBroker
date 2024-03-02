package hu.nagya.cryptobroker.domain.errors

sealed interface AppError {
    data object DefaultError : AppError
}

fun Throwable.toAppError(): AppError {
    return AppError.DefaultError
}