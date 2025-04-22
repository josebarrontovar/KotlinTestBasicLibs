package com.example.kotlintest_lib.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_table")
data class AuthEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "accessToken") val accessToken: String,
    @ColumnInfo(name = "refreshToken") val refreshToken: String
){}