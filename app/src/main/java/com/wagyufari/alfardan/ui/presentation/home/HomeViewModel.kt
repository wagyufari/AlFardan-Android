package com.wagyufari.alfardan.ui.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wagyufari.alfardan.base.BaseViewModel
import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.usecase.user.UserUseCases
import com.wagyufari.dzikirqu.base.BaseNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userUseCases: UserUseCases) :
    BaseViewModel<BaseNavigator>() {

    val users = MutableLiveData<List<User>>(listOf())

    fun getUsers() {
        userUseCases.getUsersUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    users.value = result.data
                    navigator?.hideLoading()
                }
                is Resource.Error -> {
                    navigator?.hideLoading()
                    navigator?.showToast("Error ${result.message}")
                }
                is Resource.Loading -> {
                    navigator?.showLoading()
                }
            }
        }.launchIn(viewModelScope)
    }
}