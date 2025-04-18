package com.example.kotlintest_lib.data.model.Category

import com.example.kotlintest_lib.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponseDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("creationAt")
    val creationAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String

) {
    fun toDomain(): Category {
        return Category(
            id = id,
            name = name,
            slug = slug,
            image = image,
            creationAt = creationAt,
            updatedAt = updatedAt
        )
    }
}
