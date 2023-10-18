package com.wagyufari.alfardan.ui.data.repositories

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.core.fetchData
import com.wagyufari.alfardan.ui.data.datasource.CurrencyApi
import com.wagyufari.alfardan.ui.data.datasource.UserApi
import com.wagyufari.alfardan.ui.domain.model.Currency
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.repository.CurrencyRepository
import com.wagyufari.alfardan.ui.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val api: CurrencyApi): CurrencyRepository {
    override fun getCurrencies(): Flow<Resource<List<Currency>>> {
        return flow {
            emit(Resource.Loading())
            fetchData(onFetch = {
                api.getCurrencies()
            }, onSuccess = {
                emit(Resource.Success(data = it))
            })
        }
    }
}