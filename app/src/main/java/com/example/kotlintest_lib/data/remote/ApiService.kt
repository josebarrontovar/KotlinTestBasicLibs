package com.example.kotlintest_lib.data.remote

import com.example.kotlintest_lib.data.model.AuthResponseDto.AuthResponseDto
import com.example.kotlintest_lib.data.model.LoginDto.LoginRequestDto
import com.example.kotlintest_lib.data.model.Product.ProductResponseDto
import com.example.kotlintest_lib.data.model.Profile.ProfileResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<AuthResponseDto>

    @GET("auth/profile")
    suspend fun getProfile(): Response<ProfileResponseDto>

    @GET("products")
    suspend fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<List<ProductResponseDto>>

}