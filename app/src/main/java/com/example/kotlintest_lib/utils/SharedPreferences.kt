package com.example.kotlintest_lib.utils

import android.content.Context

class SharedPreferences {

    fun saveString(key: String, value: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences("PrefTest", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("PrefTest", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }
}