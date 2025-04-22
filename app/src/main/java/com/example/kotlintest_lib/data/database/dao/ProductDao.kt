package com.example.kotlintest_lib.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.kotlintest_lib.data.database.entities.ProductEntity
import com.example.kotlintest_lib.data.database.relations.ProductWithCategory

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<ProductEntity>

    @Transaction
    @Query("SELECT * FROM product_table")
    suspend fun getProductsWithCategory(): List<ProductWithCategory>
}