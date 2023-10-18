package com.wagyufari.alfardan.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.wagyufari.dzikirqu.base.BaseNavigator

import java.lang.ref.WeakReference


abstract class BaseViewModel<N: BaseNavigator>() : ViewModel() {

    private val TAG = "BaseViewModel"

    private lateinit var mNavigator: WeakReference<N?>
    var navigator: N?
        get() = mNavigator.get()
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    var lifecycleOwner: LifecycleOwner? = null
}
