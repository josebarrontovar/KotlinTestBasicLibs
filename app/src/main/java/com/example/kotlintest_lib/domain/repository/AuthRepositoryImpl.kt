package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.data.database.dao.AuthDao
import com.example.kotlintest_lib.data.database.entities.AuthEntity
import com.example.kotlintest_lib.data.mappers.toEntity
import com.example.kotlintest_lib.data.mappers.toModel
import com.example.kotlintest_lib.domain.model.AuthModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dbAuth: AuthDao) : AuthRepository {
    override suspend fun getAuthDB(): AuthModel {
        val entity = dbAuth.getAuthById(1) ?: AuthEntity(0, "", "")
        return entity.toModel()
    }

    override suspend fun deleteAuthById(id: Int) {
        dbAuth.deleteAuthById(id)
    }

    override suspend fun resetAutoIncrement() {
        dbAuth.resetAutoIncrement()
    }

    override suspend fun insert(entity: AuthModel) {
        val entity=dbAuth.insert(entity.toEntity())
    }
}