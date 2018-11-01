package com.antonioleiva.androidmvvm.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.antonioleiva.androidmvvm.R
import com.antonioleiva.androidmvvm.ScreenState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(FindItemsInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.stateObservable.addObserver(::updateUI)
    }

    private fun updateUI(screenState: ScreenState<MainState>) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: MainState) {
        hideProgress()
        when (renderState) {
            is MainState.ShowItems -> setItems(renderState.items)
            is MainState.ShowMessage -> showMessage(renderState.message)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        list.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun setItems(items: List<String>) {
        list.adapter = MainAdapter(items, viewModel::onItemClicked)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
