package com.wagyufari.alfardan.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CommonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build())
    }
}