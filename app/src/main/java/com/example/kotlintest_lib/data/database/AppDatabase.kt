package com.example.kotlintest_lib.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlintest_lib.data.database.coverts.Converters
import com.example.kotlintest_lib.data.database.dao.AuthDao
import com.example.kotlintest_lib.data.database.dao.CategoryDao
import com.example.kotlintest_lib.data.database.dao.ProductDao
import com.example.kotlintest_lib.data.database.entities.AuthEntity
import com.example.kotlintest_lib.data.database.entities.CategoryEntity
import com.example.kotlintest_lib.data.database.entities.ProductEntity

@Database(entities = [AuthEntity::class, ProductEntity::class, CategoryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}