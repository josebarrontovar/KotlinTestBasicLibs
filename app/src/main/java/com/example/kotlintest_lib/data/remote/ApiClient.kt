package com.example.kotlintest_lib.data.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kotlintest_lib.domain.repository.LoginRepository
import com.example.kotlintest_lib.domain.repository.LoginRepositoryImpl
import com.example.kotlintest_lib.domain.repository.ProductRepository
import com.example.kotlintest_lib.domain.repository.ProductRepositoryImpl
import com.example.kotlintest_lib.domain.repository.ProfileRepository
import com.example.kotlintest_lib.domain.repository.ProfileRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(context))
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .alwaysReadResponseBody(true)
                    .build()
            )
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val loginRepository: LoginRepository by lazy {
        LoginRepositoryImpl(retrofit.create(ApiService::class.java))
    }

    val profileRepository: ProfileRepository by lazy {
        ProfileRepositoryImpl(retrofit.create(ApiService::class.java))
    }

    val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(retrofit.create(ApiService::class.java))
    }

    /* val userRepository: UserRepository by lazy {
        UserRepositoryImpl(retrofit.create(UserService::class.java))
    }

     */

}
