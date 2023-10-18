package com.wagyufari.alfardan.ui.data.datasource

import com.wagyufari.alfardan.ui.domain.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("/user")
    suspend fun getUsers(): List<User>
}