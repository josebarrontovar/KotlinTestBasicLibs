package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.data.remote.ApiService
import com.example.kotlintest_lib.domain.model.Product

class ProductRepositoryImpl(private val apiService: ApiService) : ProductRepository {
    override suspend fun getProductList(id: String): List<Product> {
        val response = apiService.getProducts(Integer.valueOf(id), Integer.valueOf(10))
        if (response.isSuccessful) {
            return response.body()?.map { it.toDomain() } ?: emptyList()
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Login failed: ${response.code()} - ${response.message()}" +
                    if (!errorBody.isNullOrBlank()) "\nError body: $errorBody" else ""
            throw Exception(errorMessage)
        }

    }
}