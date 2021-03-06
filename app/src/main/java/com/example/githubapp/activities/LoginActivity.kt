package com.example.githubapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.githubapp.R
import com.example.githubapp.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        login_btn.setOnClickListener {
            val email: String = login_et.text.toString()
            val password: String = password_et.text.toString()
            loginViewModel.login(email, password)
        }

        loginViewModel.authorizationResponse.observe(this, Observer {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        })

    }
}
