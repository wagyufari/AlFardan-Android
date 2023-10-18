package com.wagyufari.alfardan.ui.data.datasource

import com.wagyufari.alfardan.ui.domain.model.Currency
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/v1/13759cf1-8cea-480f-b93b-2d7b0c486f81")
    suspend fun getCurrencies(): List<Currency>
}