package com.wagyufari.alfardan.ui.presentation.sendmoney.currencypicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.wagyufari.alfardan.BR
import com.wagyufari.alfardan.R
import com.wagyufari.alfardan.base.BaseApplication
import com.wagyufari.alfardan.databinding.ActivityCurrencyPickerBinding
import com.wagyufari.alfardan.databinding.ActivityHomeBinding
import com.wagyufari.alfardan.ui.data.Prefs
import com.wagyufari.alfardan.ui.presentation.home.utils.ItemUserFactory
import com.wagyufari.alfardan.ui.presentation.sendmoney.SendMoneyActivity
import com.wagyufari.alfardan.ui.presentation.sendmoney.currencypicker.utils.ItemCurrencyFactory
import com.wagyufari.dzikirqu.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyPickerActivity : BaseActivity<ActivityCurrencyPickerBinding, CurrencyPickerViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_currency_picker
    override val viewModel: CurrencyPickerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewModel.navigator = this
        viewModel.getCurrencies()

        val itemAdapter = ItemAdapter<ItemCurrencyFactory>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        fastAdapter.onClickListener = { view, adapter, item, position ->
            val resultIntent = Intent()
            resultIntent.putExtra("Currency", item.currency)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
            true
        }
        viewDataBinding.rvCurrencies.layoutManager = LinearLayoutManager(this)
        viewDataBinding.rvCurrencies.adapter = fastAdapter

        viewModel.currencies.observe(this) {
            itemAdapter.add(it.map {
                ItemCurrencyFactory().apply {
                    currency = it
                }
            })
        }

    }
}