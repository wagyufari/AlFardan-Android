package com.wagyufari.alfardan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wagyufari.alfardan.R
import com.wagyufari.alfardan.ui.data.Prefs
import com.wagyufari.alfardan.ui.presentation.auth.login.AuthLoginActivity
import com.wagyufari.alfardan.ui.presentation.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            Prefs.accessToken?.let {
                startActivity(Intent(this, HomeActivity::class.java))
            } ?: kotlin.run {
                startActivity(Intent(this, AuthLoginActivity::class.java))
            }
            finish()
        }, 1500)

    }
}