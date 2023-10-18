package com.wagyufari.alfardan.ui.presentation.auth.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.wagyufari.alfardan.R
import com.wagyufari.alfardan.BR
import com.wagyufari.alfardan.databinding.ActivityAuthLoginBinding
import com.wagyufari.alfardan.ui.presentation.auth.register.AuthRegisterActivity
import com.wagyufari.alfardan.ui.presentation.home.HomeActivity
import com.wagyufari.dzikirqu.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthLoginActivity : BaseActivity<ActivityAuthLoginBinding, AuthLoginViewModel>(), AuthLoginNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_auth_login
    override val viewModel: AuthLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewModel.navigator = this
        setNoAccountText()

        viewDataBinding.buttonLogin.setOnClickListener {
            viewModel.authenticateUser()
        }
    }

    fun setNoAccountText(){
        val fullText = "Don't have an account? Sign Up"
        val spannableString = SpannableString(fullText)
        val startIndex = fullText.indexOf("Sign Up")
        val endIndex = startIndex + "Sign Up".length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, 0)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)), startIndex, endIndex, 0)
        viewDataBinding.haveAccountText.text = spannableString
        viewDataBinding.haveAccountText.setOnClickListener {
            startActivity(Intent(this, AuthRegisterActivity::class.java))
        }
    }

    override fun onSuccessLogin() {
        showToast("Login Success")
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}