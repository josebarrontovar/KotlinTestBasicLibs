package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.data.model.LoginDto.LoginRequestDto
import com.example.kotlintest_lib.data.remote.ApiService
import com.example.kotlintest_lib.domain.model.AuthTokens
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: ApiService) : LoginRepository {

    override suspend fun login(
        fullName: String,
        password: String
    ): AuthTokens {
        val request = LoginRequestDto(fullName, password)
        val response = apiService.login(request)

        if (response.isSuccessful) {
            return response.body()?.toDomain() ?: throw Exception("Empty response body") // Handle empty body
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Login failed: ${response.code()} - ${response.message()}" +
                    if (!errorBody.isNullOrBlank()) "\nError body: $errorBody" else ""
            throw Exception(errorMessage)
        }
    }
}