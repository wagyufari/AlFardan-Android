package com.wagyufari.alfardan.ui.domain.repository

import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<Resource<List<User>>>
}