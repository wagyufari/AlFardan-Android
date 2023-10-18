package com.wagyufari.alfardan.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build();
    }

    companion object {
        @get:Synchronized
        var instance: BaseApplication? = null
            private set

        fun restartApp(ctx: Context){
            val pm = ctx.packageManager
            val intent = pm.getLaunchIntentForPackage(ctx.packageName)
            val mainIntent = Intent.makeRestartActivityTask(intent!!.component)
            ctx.startActivity(mainIntent)
            Runtime.getRuntime().exit(0)
        }
    }
}
