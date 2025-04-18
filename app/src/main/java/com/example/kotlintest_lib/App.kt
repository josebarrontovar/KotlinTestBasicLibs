package com.example.kotlintest_lib

import android.app.Application
import com.example.kotlintest_lib.data.remote.ApiClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}