package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
}