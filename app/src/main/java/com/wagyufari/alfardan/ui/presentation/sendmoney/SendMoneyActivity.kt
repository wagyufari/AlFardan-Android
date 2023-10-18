package com.wagyufari.alfardan.ui.presentation.sendmoney

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.wagyufari.alfardan.BR
import com.wagyufari.alfardan.R
import com.wagyufari.alfardan.databinding.ActivityHomeBinding
import com.wagyufari.alfardan.databinding.ActivitySendMoneyBinding
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.presentation.sendmoney.currencypicker.CurrencyPickerActivity
import com.wagyufari.alfardan.utils.formatDoubleWithMaxFraction
import com.wagyufari.alfardan.utils.parcelable
import com.wagyufari.dzikirqu.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendMoneyActivity : BaseActivity<ActivitySendMoneyBinding, SendMoneyViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_send_money
    override val viewModel: SendMoneyViewModel by viewModels()

    companion object {
        private const val EXTRA_USER = "type"

        fun start(context: Context, user: User?) {
            context.startActivity(
                Intent(
                    context, SendMoneyActivity::class.java
                ).apply {
                    putExtra(EXTRA_USER, user)
                })
        }

        fun SendMoneyActivity.getUser(): User? {
            return intent.parcelable(EXTRA_USER)
        }
    }

    val currencyPickerContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            viewModel.selectedCurrency.value = data?.parcelable("Currency")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewModel.navigator = this
        viewModel.user.value = getUser()

        Glide
            .with(this)
            .load(viewModel.user.value?.profilePicture)
            .centerCrop()
            .transform(CircleCrop())
            .into(viewDataBinding.userProfilePicture)

        viewModel.getCurrencies()

        viewModel.selectedCurrency.observe(this){
            viewModel.receivingCurrencyCode.value = it?.code
            handleConversion()
        }

        viewModel.nominal.observe(this){
            handleConversion()
        }

        viewDataBinding.currencyContainer.setOnClickListener {
            val intent = Intent(this, CurrencyPickerActivity::class.java)
            currencyPickerContract.launch(intent)
        }

        viewDataBinding.btnClose.setOnClickListener {
            finish()
        }

        handleState()
        handleNumericButtons()
    }

    fun handleState(){

        viewModel.state.observe(this){
            handleConversion()
        }

        viewDataBinding.tvCurrencySwap.setOnClickListener {
            if(viewModel.state.value == SendMoneyState.SendingCurrency) {
                viewModel.state.value = SendMoneyState.ReceivingCurrency
            } else {
                viewModel.state.value = SendMoneyState.SendingCurrency
            }
            val convertedNominal = if(viewModel.convertedNominal.value?.isEmpty() == true) 0.0 else viewModel.convertedNominal.value?.toDouble() ?: 0.0
            viewModel.nominal.value = formatDoubleWithMaxFraction(convertedNominal, 2)
        }
    }

    fun handleConversion(){
        viewModel.convertedNominal.value = formatDoubleWithMaxFraction(getConverted(), 2)
    }

    fun getConverted(): Double {
        val nominal = if(viewModel.nominal.value?.isEmpty() == true) 0.0 else viewModel.nominal.value?.toDouble() ?: 0.0
        val exchangeRate = viewModel.selectedCurrency.value?.exchangeRateToAed ?: 0.0
        return if (viewModel.state.value == SendMoneyState.SendingCurrency) nominal * exchangeRate else nominal / exchangeRate
    }

    fun handleNumericButtons(){
        viewDataBinding.tv1.setOnClickListener {
            viewModel.nominal.value += "1"
        }
        viewDataBinding.tv2.setOnClickListener {
            viewModel.nominal.value += "2"
        }
        viewDataBinding.tv3.setOnClickListener {
            viewModel.nominal.value += "3"
        }
        viewDataBinding.tv4.setOnClickListener {
            viewModel.nominal.value += "4"
        }
        viewDataBinding.tv5.setOnClickListener {
            viewModel.nominal.value += "5"
        }
        viewDataBinding.tv6.setOnClickListener {
            viewModel.nominal.value += "6"
        }
        viewDataBinding.tv7.setOnClickListener {
            viewModel.nominal.value += "7"
        }
        viewDataBinding.tv8.setOnClickListener {
            viewModel.nominal.value += "8"
        }
        viewDataBinding.tv9.setOnClickListener {
            viewModel.nominal.value += "9"
        }
        viewDataBinding.tv0.setOnClickListener {
            viewModel.nominal.value += "0"
        }
        viewDataBinding.tvDot.setOnClickListener {
            viewModel.nominal.value += "."
        }
        viewDataBinding.ivBackspace.setOnClickListener {
            viewModel.nominal.value = deleteLastCharacter(viewModel.nominal.value.toString())
        }

    }

    fun deleteLastCharacter(input: String): String {
        if (input.isEmpty()) {
            return input // Handle empty string case
        }

        return input.substring(0, input.length - 1)
    }
}

