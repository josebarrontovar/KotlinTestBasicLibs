package com.example.kotlintest_lib.domain.model

import com.example.kotlintest_lib.data.database.relations.ProductWithCategory

data class Product(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Int,
    val description: String,
    val category: Category,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String
)

fun ProductWithCategory.toDomain(): Product {
    return Product(
        id = product.id,
        title = product.title,
        slug = product.slug,
        price = product.price,
        description = product.description,
        category = Category(
            id = category.id,
            name = category.name,
            slug = category.slug,
            image = category.image,
            creationAt = category.creationAt,
            updatedAt = category.updatedAt
        ),
        images = product.images,
        creationAt = product.creationAt,
        updatedAt = product.updatedAt
    )
}