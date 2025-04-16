package com.example.kotlintest_lib

import android.app.Application
import com.example.kotlintest_lib.data.remote.ApiClient

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.init(this)
    }
}