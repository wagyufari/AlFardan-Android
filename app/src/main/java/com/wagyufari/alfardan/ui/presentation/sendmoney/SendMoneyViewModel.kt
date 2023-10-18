package com.wagyufari.alfardan.ui.presentation.sendmoney

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wagyufari.alfardan.base.BaseViewModel
import com.wagyufari.alfardan.core.Resource
import com.wagyufari.alfardan.ui.domain.model.Currency
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.domain.usecase.currency.CurrencyUseCases
import com.wagyufari.dzikirqu.base.BaseNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor(val currencyUseCases: CurrencyUseCases) :
    BaseViewModel<BaseNavigator>() {

    val currencies = MutableLiveData<List<Currency>>(listOf())
    val selectedCurrency = MutableLiveData<Currency?>()

    val user = MutableLiveData<User?>()
    val nominal = MutableLiveData("")
    val convertedNominal = MutableLiveData("")

    val sendingCurrencyCode = MutableLiveData("AED")
    val receivingCurrencyCode = MutableLiveData("USD")

    val state = MutableLiveData(SendMoneyState.SendingCurrency)

    fun getCurrencies(){
        currencyUseCases.getCurrenciesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    currencies.value = result.data
                    selectedCurrency.value = currencies.value?.first()
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

