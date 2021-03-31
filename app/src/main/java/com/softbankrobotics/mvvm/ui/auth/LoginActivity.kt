package com.softbankrobotics.mvvm.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.softbankrobotics.mvvm.R
import com.softbankrobotics.mvvm.databinding.ActivityLoginBinding
import com.softbankrobotics.mvvm.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // get the viewmodel
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        // set the viewmodel to my bindind
        binding.viewmodel = viewModel

        // get the data from the ui
        viewModel.authListener = this // assign this due to this current class contains my authListener
    }

    override fun onStarted() {
        toast("Login started")
    }

    override fun onSuccess() {
        toast("Login success")
    }

    override fun onFailure(reason: String) {
        toast("Login failed for $reason")
    }

}