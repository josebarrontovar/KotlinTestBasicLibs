package com.example.kotlintest_lib.domain.repository

import com.example.kotlintest_lib.data.database.dao.CategoryDao
import com.example.kotlintest_lib.data.database.dao.ProductDao
import com.example.kotlintest_lib.data.remote.ApiService
import com.example.kotlintest_lib.domain.model.Product
import com.example.kotlintest_lib.data.database.relations.ProductWithCategory
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao
) : ProductRepository {
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

    override suspend fun insertProduct(product: List<ProductWithCategory>) {
        val categories = product.map { it.category }
        categories.forEach { it ->
            categoryDao.insert(it)
        }
        product.forEach { it ->
            productDao.insert(it.product)
        }


    }

    override suspend fun getProductsWithCategory(): List<ProductWithCategory> {
        return productDao.getProductsWithCategory().map { productWithCategoryDB ->
            ProductWithCategory(
                product = productWithCategoryDB.product,
                category = productWithCategoryDB.category
            )
        }
    }
}