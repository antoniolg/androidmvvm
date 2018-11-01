package com.antonioleiva.androidmvvm

import android.os.Handler

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}