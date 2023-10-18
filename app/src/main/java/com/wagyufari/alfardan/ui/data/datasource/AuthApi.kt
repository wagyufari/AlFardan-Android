package com.wagyufari.alfardan.ui.data.datasource

import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.model.UserResponseModel
import retrofit2.http.GET

interface AuthApi {

    @GET("/v1/a67e09fc-efdf-4530-b6c6-293ce13856cb")
    suspend fun authenticateUser(): UserResponseModel

    @GET("/v1/a67e09fc-efdf-4530-b6c6-293ce13856cb")
    suspend fun registerUser(): User

}