package com.example.kotlintest_lib.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = context.getSharedPreferences("PrefTest", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("access_token", null)
        val requestBuilder= chain.request().newBuilder()
        token?.let{
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }

}