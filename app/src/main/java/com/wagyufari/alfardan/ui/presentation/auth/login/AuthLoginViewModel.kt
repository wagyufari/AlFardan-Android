package com.wagyufari.alfardan.ui.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wagyufari.alfardan.base.BaseViewModel
import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.usecase.auth.AuthUseCases
import com.wagyufari.dzikirqu.base.BaseNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthLoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) :
    BaseViewModel<AuthLoginNavigator>() {

    val email = MutableLiveData("g@g.com")
    val password = MutableLiveData("a")

    fun authenticateUser() {

        if (email.value?.isEmpty() == true) {
            navigator?.showToast("Email cannot be empty")
            return
        }

        if (password.value?.isEmpty() == true) {
            navigator?.showToast("Email cannot be empty")
            return
        }

        if (!isValidEmail(email.value.toString())) {
            navigator?.showToast("Invalid email format")
            return
        }

        authUseCases.authenticateUserUseCase.invoke(
            email.value.toString(),
            password.value.toString()
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    navigator?.hideLoading()
                    navigator?.onSuccessLogin()
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

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})([.]{1})(.{1,})"

        return email.matches(emailRegex.toRegex())
    }
}