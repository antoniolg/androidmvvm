package com.antonioleiva.androidmvvm.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.antonioleiva.androidmvvm.R
import com.antonioleiva.androidmvvm.ScreenState
import com.antonioleiva.androidmvvm.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel = LoginViewModel(LoginInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.stateObservable.addObserver(::updateUI)

        button.setOnClickListener { onLoginClicked() }
    }

    private fun updateUI(screenState: ScreenState<LoginState>) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processLoginState(screenState.renderState)
        }
    }

    private fun processLoginState(renderState: LoginState) {
        progress.visibility = View.GONE
        when (renderState) {
            LoginState.Success -> startActivity(Intent(this, MainActivity::class.java))
            LoginState.WrongUserName -> username.error = getString(R.string.username_error)
            LoginState.WrongPassword -> password.error = getString(R.string.password_error)
        }
    }

    private fun onLoginClicked() {
        viewModel.onLoginClicked(username.text.toString(), password.text.toString())
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}
