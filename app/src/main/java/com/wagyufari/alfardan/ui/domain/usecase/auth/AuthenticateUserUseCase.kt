package com.wagyufari.alfardan.ui.domain.usecase.auth

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.data.Prefs
import com.wagyufari.alfardan.ui.domain.model.UserResponseModel
import com.wagyufari.alfardan.ui.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthenticateUserUseCase(val repo: AuthRepository){

    operator fun invoke(email: String, password: String): Flow<Resource<UserResponseModel>> {
        return repo.authenticateUser(email, password).map {
            Prefs.accessToken = it.data?.token
            it
        }
    }
}