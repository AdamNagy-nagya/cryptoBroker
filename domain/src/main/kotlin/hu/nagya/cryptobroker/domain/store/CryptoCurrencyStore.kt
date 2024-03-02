package hu.nagya.cryptobroker.domain.store

import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface CryptoCurrencyStore {
    fun getAll(): Flow<List<CryptoCurrency>>
    fun get(id: String): Flow<CryptoCurrency>
}
