package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.data.remote.ApiService
import com.example.kotlintest_lib.domain.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val apiService: ApiService) : ProfileRepository {
    override suspend fun getProfile(): Profile {
        val response = apiService.getProfile()
        if (response.isSuccessful) {
            return response.body()?.toDomain()
                ?: throw Exception("Empty response body") // Handle empty body
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Login failed: ${response.code()} - ${response.message()}" +
                    if (!errorBody.isNullOrBlank()) "\nError body: $errorBody" else ""
            throw Exception(errorMessage)
        }


    }


}