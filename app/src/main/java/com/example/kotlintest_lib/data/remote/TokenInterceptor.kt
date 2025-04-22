package com.example.kotlintest_lib.data.remote

import android.content.Context
import com.example.kotlintest_lib.data.database.dao.AuthDao
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val context: Context, private val db:AuthDao ) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = context.getSharedPreferences("PrefTest", Context.MODE_PRIVATE)
        val token = runBlocking {
            db.getAuthById(1)?.accessToken
        }
        val requestBuilder= chain.request().newBuilder()
        token?.let{
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }

}