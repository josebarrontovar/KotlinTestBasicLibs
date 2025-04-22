package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.domain.model.Product
import com.example.kotlintest_lib.data.database.relations.ProductWithCategory

interface ProductRepository {
    suspend fun getProductList(id: String): List<Product>
    suspend fun insertProduct(product: List<ProductWithCategory>)
    suspend fun getProductsWithCategory() : List<ProductWithCategory>
}