package com.example.kotlintest_lib.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlintest_lib.data.database.entities.AuthEntity

@Dao
interface AuthDao {
    @Insert
    suspend fun insert(entity: AuthEntity)

    @Query("SELECT * FROM auth_table WHERE id=:id")
    suspend fun getAuthById(id: Int): AuthEntity?
}