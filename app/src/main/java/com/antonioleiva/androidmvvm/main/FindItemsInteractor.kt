package com.antonioleiva.androidmvvm.main

import com.antonioleiva.androidmvvm.postDelayed

class FindItemsInteractor {

    fun findItems(callback: (List<String>) -> Unit) {
        postDelayed(2000) { callback(createArrayList()) }
    }

    private fun createArrayList(): List<String> = (1..10).map { "Item $it" }
}