package com.wagyufari.alfardan.ui.presentation.sendmoney.currencypicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wagyufari.alfardan.base.BaseViewModel
import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.Currency
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.usecase.currency.CurrencyUseCases
import com.wagyufari.alfardan.ui.domain.usecase.user.UserUseCases
import com.wagyufari.dzikirqu.base.BaseNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyPickerViewModel @Inject constructor(private val currencyUseCases: CurrencyUseCases) :
    BaseViewModel<BaseNavigator>() {

    val currencies = MutableLiveData<List<Currency>>(listOf())

    fun getCurrencies() {
        currencyUseCases.getCurrenciesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    currencies.value = result.data
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