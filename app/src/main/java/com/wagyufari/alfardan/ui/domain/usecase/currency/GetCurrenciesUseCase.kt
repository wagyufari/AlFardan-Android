package com.wagyufari.alfardan.ui.domain.usecase.currency

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.Currency
import com.wagyufari.alfardan.ui.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetCurrenciesUseCase(val repo: CurrencyRepository){

    operator fun invoke(): Flow<Resource<List<Currency>>> {
        return repo.getCurrencies()
    }
}
