package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.domain.model.AuthTokens

interface LoginRepository {
    suspend fun login(fullName: String, password: String): AuthTokens
}