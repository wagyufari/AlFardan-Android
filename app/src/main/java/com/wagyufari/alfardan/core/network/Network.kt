package com.wagyufari.alfardan.core.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.wagyufari.alfardan.core.network.interceptor.CommonInterceptor
import com.wagyufari.alfardan.core.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun retrofitClient(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient(context))
            .build()
    }

    fun retrofitUserClient(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_USERS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient(context))
            .build()
    }

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(CommonInterceptor())
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .build()
    }
}