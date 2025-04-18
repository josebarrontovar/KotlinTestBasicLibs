package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.domain.model.Product

interface ProductRepository {
    suspend fun getProductList(id: String): List<Product>
}