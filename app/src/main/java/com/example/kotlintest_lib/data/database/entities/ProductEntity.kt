package com.example.kotlintest_lib.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "categoryId") val categoryId: Int,
    @ColumnInfo(name = "images") val images: List<String>,
    @ColumnInfo(name = "creationAt") val creationAt: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String
)