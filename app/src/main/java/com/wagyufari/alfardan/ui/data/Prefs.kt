package com.wagyufari.alfardan.ui.data

import com.orhanobut.hawk.Hawk

object Prefs {

    var accessToken: String?
        get() = Hawk.get("AccessToken")
        set(value) {
            Hawk.put("AccessToken", value)
        }

}
