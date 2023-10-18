package com.wagyufari.alfardan.ui.presentation.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.wagyufari.alfardan.BR
import com.wagyufari.alfardan.R
import com.wagyufari.alfardan.base.BaseApplication
import com.wagyufari.alfardan.databinding.ActivityHomeBinding
import com.wagyufari.alfardan.ui.data.Prefs
import com.wagyufari.alfardan.ui.domain.model.User
import com.wagyufari.alfardan.ui.presentation.home.utils.ItemUserFactory
import com.wagyufari.dzikirqu.base.BaseActivity
import com.wagyufari.alfardan.ui.presentation.home.HomeViewModel
import com.wagyufari.alfardan.ui.presentation.sendmoney.SendMoneyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewModel.navigator = this
        viewModel.getUsers()

        val itemAdapter = ItemAdapter<ItemUserFactory>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        fastAdapter.onClickListener = { view, adapter, item, position ->
            SendMoneyActivity.start(this@HomeActivity, item.user)
            true
        }
        viewDataBinding.rvUsers.layoutManager = LinearLayoutManager(this)
        viewDataBinding.rvUsers.adapter = fastAdapter

        viewDataBinding.btnLogout.setOnClickListener {
            Prefs.accessToken = null
            BaseApplication.restartApp(this)
        }

        viewModel.users.observe(this) {
            itemAdapter.add(it.map {
                ItemUserFactory().apply {
                    user = it
                }
            })
        }

    }
}