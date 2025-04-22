package com.example.kotlintest_lib.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.kotlintest_lib.data.database.entities.CategoryEntity
import com.example.kotlintest_lib.data.database.entities.ProductEntity

data class ProductWithCategory(
    @Embedded val product: ProductEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)