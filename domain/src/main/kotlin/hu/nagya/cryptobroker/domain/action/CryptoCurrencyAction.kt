package hu.nagya.cryptobroker.domain.action

interface CryptoCurrencyAction {
    suspend fun refreshAll()
    suspend fun refresh(id: String)
}
