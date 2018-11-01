package com.antonioleiva.androidmvvm.login

import com.antonioleiva.androidmvvm.Observable
import com.antonioleiva.androidmvvm.ScreenState

class LoginViewModel(private val loginInteractor: LoginInteractor) :
    LoginInteractor.OnLoginFinishedListener {

    val stateObservable = Observable<ScreenState<LoginState>>()

    fun onLoginClicked(username: String, password: String) {
        stateObservable.callObservers(ScreenState.Loading)
        loginInteractor.login(username, password, this)
    }

    fun onDestroy() {
        stateObservable.clearObservers()
    }

    override fun onUsernameError() {
        stateObservable.callObservers(ScreenState.Render(LoginState.WrongUserName))
    }

    override fun onPasswordError() {
        stateObservable.callObservers(ScreenState.Render(LoginState.WrongPassword))
    }

    override fun onSuccess() {
        stateObservable.callObservers(ScreenState.Render(LoginState.Success))
    }
}