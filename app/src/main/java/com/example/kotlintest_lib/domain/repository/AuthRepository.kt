package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.domain.model.AuthModel

interface AuthRepository {

    suspend fun getAuthDB(): AuthModel
    suspend fun deleteAuthById(id: Int)
    suspend fun resetAutoIncrement()
    suspend fun insert(entity: AuthModel)
}