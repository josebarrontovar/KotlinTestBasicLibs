package com.example.kotlintest_lib.data.mappers

import com.example.kotlintest_lib.data.database.entities.AuthEntity
import com.example.kotlintest_lib.domain.model.AuthModel


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
