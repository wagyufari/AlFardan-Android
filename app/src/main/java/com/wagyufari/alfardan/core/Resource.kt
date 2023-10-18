package com.wagyufari.alfardan.core

import kotlinx.coroutines.flow.FlowCollector
import retrofit2.HttpException
import java.io.IOException

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

suspend fun <T> FlowCollector<Resource<T>>.fetchData(onFetch: suspend () -> T, onSuccess: suspend (T) -> Unit) {
    try {
        onSuccess(onFetch())
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        try {
            emit(Resource.Error(errorBody.toString()))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } catch (e: IOException) {
        emit(Resource.Error("Couldn't reach server. Check your internet connection."))
    }
}