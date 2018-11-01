package com.antonioleiva.androidmvvm.main

import com.antonioleiva.androidmvvm.Observable
import com.antonioleiva.androidmvvm.ScreenState

class MainViewModel(private val findItemsInteractor: FindItemsInteractor) {

    val stateObservable = Observable<ScreenState<MainState>>()

    fun onResume() {
        stateObservable.callObservers(ScreenState.Loading)
        findItemsInteractor.findItems(::onItemsLoaded)
    }

    private fun onItemsLoaded(items: List<String>) {
        stateObservable.callObservers(ScreenState.Render(MainState.ShowItems(items)))
    }

    fun onItemClicked(item: String) {
        stateObservable.callObservers(ScreenState.Render(MainState.ShowMessage(item)))
    }

    fun onDestroy() {
        stateObservable.clearObservers()
    }
}