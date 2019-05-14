package com.antonioleiva.androidmvvm.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antonioleiva.androidmvvm.ScreenState

class LoginViewModel(private val loginInteractor: LoginInteractor) : ViewModel(),
    LoginInteractor.OnLoginFinishedListener {

    private val _loginState: MutableLiveData<ScreenState<LoginState>> = MutableLiveData()

    val loginState: LiveData<ScreenState<LoginState>>
        get() = _loginState

    fun onLoginClicked(username: String, password: String) {
        _loginState.value = ScreenState.Loading
        loginInteractor.login(username, password, this)
    }

    override fun onUsernameError() {
        _loginState.value = ScreenState.Render(LoginState.WrongUserName)
    }

    override fun onPasswordError() {
        _loginState.value = ScreenState.Render(LoginState.WrongPassword)
    }

    override fun onSuccess() {
        _loginState.value = ScreenState.Render(LoginState.Success)
    }
}

class LoginViewModelFactory(private val loginInteractor: LoginInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginInteractor) as T
    }
}