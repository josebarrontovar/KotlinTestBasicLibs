package com.example.kotlintest_lib.data.model.Product

import com.example.kotlintest_lib.data.model.Category.CategoryResponseDto
import com.example.kotlintest_lib.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductResponseDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("category")
    val category: CategoryResponseDto,

    @SerializedName("images")
    val images: List<String>,

    @SerializedName("creationAt")
    val creationAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String

) {
    fun toDomain(): Product {
        return Product(
            id = id,
            title = title,
            slug = slug,
            price = price,
            description = description,
            category = category.toDomain(),
            images = images,
            creationAt = creationAt,
            updatedAt = updatedAt
        )
    }
}
