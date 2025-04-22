package com.example.kotlintest_lib.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlintest_lib.data.database.dao.AuthDao
import com.example.kotlintest_lib.data.database.entities.AuthEntity

@Database(entities = [AuthEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDao(): AuthDao
}