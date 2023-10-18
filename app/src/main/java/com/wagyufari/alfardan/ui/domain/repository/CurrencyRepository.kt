package com.wagyufari.alfardan.ui.domain.repository

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.Currency
import com.wagyufari.alfardan.ui.domain.model.User
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencies(): Flow<Resource<List<Currency>>>
}