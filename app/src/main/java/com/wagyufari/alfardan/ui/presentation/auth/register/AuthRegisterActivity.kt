package com.wagyufari.alfardan.ui.presentation.auth.register

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
import com.wagyufari.alfardan.databinding.ActivityAuthRegisterBinding
import com.wagyufari.dzikirqu.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthRegisterActivity : BaseActivity<ActivityAuthRegisterBinding, AuthRegisterViewModel>(), AuthRegisterNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_auth_register
    override val viewModel: AuthRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewModel.navigator = this
        setHaveAccountText()

        viewDataBinding.buttonRegister.setOnClickListener {
            viewModel.registerUser()
        }
    }

    fun setHaveAccountText(){
        val fullText = "Already have an account? Sign in"
        val spannableString = SpannableString(fullText)
        val startIndex = fullText.indexOf("Sign in")
        val endIndex = startIndex + "Sign in".length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, 0)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)), startIndex, endIndex, 0)
        viewDataBinding.haveAccountText.text = spannableString
        viewDataBinding.haveAccountText.setOnClickListener {
            startActivity(Intent(this, AuthRegisterActivity::class.java))
        }
    }

    override fun onSuccessRegister() {
        showToast("Account successfully registered")
        finish()
    }

}