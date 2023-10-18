package com.wagyufari.alfardan.ui.domain.usecase.user

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(val repo: UserRepository){

    operator fun invoke(): Flow<Resource<List<User>>> {
        return repo.getUsers()
    }
}