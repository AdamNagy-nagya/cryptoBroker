package hu.nagya.cryptobroker.data.action

import hu.nagya.cryptobroker.data.holder.CryptoCurrencyHolder
import hu.nagya.cryptobroker.data.mapper.toDomainModel
import hu.nagya.cryptobroker.data.network.response.CryptoCurrencyApiModel
import hu.nagya.cryptobroker.data.network.service.CoinCapService
import hu.nagya.cryptobroker.domain.action.CryptoCurrencyAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoCryptoCurrencyActionImpl(
    private val cryptoCurrencyHolder: CryptoCurrencyHolder,
    private val coinCapService: CoinCapService
) : CryptoCurrencyAction {
    override suspend fun refreshAll() = withContext(Dispatchers.Default) {
        val apiModelList: List<CryptoCurrencyApiModel> = coinCapService.getAll().data

        val domainModelList = apiModelList.map { it.toDomainModel() }

        cryptoCurrencyHolder.setItem(domainModelList) // with context
    }

    override suspend fun refresh(id: String) = withContext(Dispatchers.Default) {
        val apiModel = coinCapService.get(id).data

        val domainModel = apiModel.toDomainModel()

        val currentStoredList = cryptoCurrencyHolder.getFirstItemOrNull()

        cryptoCurrencyHolder.setItem(currentStoredList?.map {
            if (it.id == domainModel.id) {
                domainModel
            } else {
                it
            }
        } ?: listOf(domainModel))
    }
}
