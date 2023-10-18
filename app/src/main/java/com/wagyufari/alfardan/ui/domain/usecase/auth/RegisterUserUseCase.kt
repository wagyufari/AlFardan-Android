package com.wagyufari.alfardan.ui.domain.usecase.auth

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow


class RegisterUserUseCase(val repo: AuthRepository){

    operator fun invoke(email: String, password: String): Flow<Resource<User>> {
        return repo.registerUser(email, password)
    }
}