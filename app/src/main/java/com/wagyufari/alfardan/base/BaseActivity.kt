package com.wagyufari.dzikirqu.base

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wagyufari.alfardan.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback,
    BaseNavigator {

    lateinit var viewDataBinding: T
        private set

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int
    private var pDialog: ProgressDialog? = null

    abstract val viewModel: V


    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onApplyWindowEvent(insets: Insets) {

    }

    override fun showToast(message: String?) {
        CoroutineScope(Main).launch {
            Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initShakeToDebugApiCall() {
//        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        val shakeDetector = ShakeDetector {
//            startActivity(Chuck.getLaunchIntent(this))
//        }
//        shakeDetector.start(sensorManager)
    }

    override fun finish() {
        super.finish()
    }

    override fun onSettingsEvent() {

    }

    fun delay(delay: Long, runnable: () -> Unit) {
        Handler().postDelayed({
            runnable.invoke()
        }, delay)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pDialog = getProgressDialog("Please Wait")
        pDialog = ProgressDialog(this)
        pDialog?.setTitle("Loading")
        pDialog?.setMessage("Please wait...")
        pDialog?.setCancelable(false)
        performDataBinding()
        initShakeToDebugApiCall()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun hideLoading() {
        CoroutineScope(Main).launch {
            if (pDialog?.isShowing == true) pDialog?.dismiss()
        }
    }

    override fun showLoading() {
        CoroutineScope(Main).launch {
            if (pDialog?.isShowing == false) pDialog?.show()
        }
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
        }
    }
}

