package hu.nagya.cryptobroker.data.store

import hu.nagya.cryptobroker.data.holder.CryptoCurrencyHolder
import hu.nagya.cryptobroker.domain.models.CryptoCurrency
import hu.nagya.cryptobroker.domain.store.CryptoCurrencyStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CryptoCurrencyStoreImpl(
    private val cryptoCurrencyHolder: CryptoCurrencyHolder
) : CryptoCurrencyStore {
    override fun getAll(): Flow<List<CryptoCurrency>> = cryptoCurrencyHolder.getItemFlow()
        .flowOn(Dispatchers.Default)

    override fun get(id: String): Flow<CryptoCurrency> = cryptoCurrencyHolder.getItemFlow()
        .map { currencies -> currencies.first { it.id == id } }
        .flowOn(Dispatchers.Default)
}
