package com.wagyufari.alfardan.ui.domain.repository

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.model.UserResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun authenticateUser(email:String, password: String): Flow<Resource<UserResponseModel>>
    fun registerUser(email:String, password: String): Flow<Resource<User>>
}