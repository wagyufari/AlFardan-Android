package com.wagyufari.alfardan.ui.data.repositories

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.core.fetchData
import com.wagyufari.alfardan.ui.data.datasource.AuthApi
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.model.UserResponseModel
import com.wagyufari.alfardan.ui.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: AuthApi): AuthRepository {

    override fun authenticateUser(
        email: String,
        password: String
    ): Flow<Resource<UserResponseModel>> {
        return flow {
            emit(Resource.Loading())
            fetchData(onFetch = {
                api.authenticateUser()
            }, onSuccess = {
                emit(Resource.Success(data = it))
            })
        }
    }

    override fun registerUser(email: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading())
            fetchData(onFetch = {
                api.registerUser()
            }, onSuccess = {
                emit(Resource.Success(data = it))
            })
        }
    }
}