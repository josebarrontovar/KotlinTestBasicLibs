package com.example.kotlintest_lib.data.mappers

import com.example.kotlintest_lib.data.database.entities.AuthEntity
import com.example.kotlintest_lib.data.database.entities.CategoryEntity
import com.example.kotlintest_lib.data.database.entities.ProductEntity
import com.example.kotlintest_lib.domain.model.AuthModel
import com.example.kotlintest_lib.domain.model.Category
import com.example.kotlintest_lib.domain.model.Product


fun AuthEntity.toModel(): AuthModel {
    return AuthModel(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}

fun AuthModel.toEntity(): AuthEntity {
    return AuthEntity(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}

fun ProductEntity.toModel(category: Category): Product {
    return Product(
        id = this.id,
        title = this.title,
        slug = this.slug,
        price = this.price,
        description = this.description,
        category = category,
        images = this.images,
        creationAt = this.creationAt,
        updatedAt = this.updatedAt
    )
}

fun CategoryEntity.toModel(): Category {
    return Category(
        id = this.id,
        name = this.name,
        slug = this.slug,
        image = this.image,
        creationAt = this.creationAt,
        updatedAt = this.updatedAt
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        slug = slug,
        price = price,
        description = description,
        categoryId = category.id,  // Usar el 'id' de Category
        images = images,
        creationAt = creationAt,
        updatedAt = updatedAt
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        slug = slug,
        image = image,
        creationAt = creationAt,
        updatedAt = updatedAt
    )
}
