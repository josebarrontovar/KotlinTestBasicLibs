package com.example.kotlintest_lib.utils

import android.content.Context
import javax.inject.Inject

class SharedPreferences @Inject constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("PrefTest", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, context: Context): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clearKey(key: String, context: Context) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}