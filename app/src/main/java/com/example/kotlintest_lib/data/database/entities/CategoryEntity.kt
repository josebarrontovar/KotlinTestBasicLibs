package com.example.kotlintest_lib.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val slug: String,
    val image: String,
    val creationAt: String,
    val updatedAt: String
)