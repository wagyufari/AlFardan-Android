package com.wagyufari.alfardan.ui.data.repositories

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.core.fetchData
import com.wagyufari.alfardan.ui.data.datasource.UserApi
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserApi): UserRepository {
    override fun getUsers(): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading())
            fetchData(onFetch = {
                api.getUsers()
            }, onSuccess = {
                emit(Resource.Success(data = it))
            })
        }
    }
}