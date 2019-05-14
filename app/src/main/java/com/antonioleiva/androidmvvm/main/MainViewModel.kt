package com.antonioleiva.androidmvvm.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antonioleiva.androidmvvm.ScreenState

class MainViewModel(private val findItemsInteractor: FindItemsInteractor) : ViewModel() {

    private lateinit var _mainState: MutableLiveData<ScreenState<MainState>>

    val mainState: LiveData<ScreenState<MainState>>
        get() {
            if (!::_mainState.isInitialized) {
                _mainState = MutableLiveData()
                _mainState.value = ScreenState.Loading
                findItemsInteractor.findItems(::onItemsLoaded)
            }
            return _mainState
        }

    private fun onItemsLoaded(items: List<String>) {
        _mainState.value = ScreenState.Render(MainState.ShowItems(items))
    }

    fun onItemClicked(item: String) {
        _mainState.value = ScreenState.Render(MainState.ShowMessage(item))
    }
}

class MainViewModelFactory(private val findItemsInteractor: FindItemsInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(findItemsInteractor) as T
    }
}