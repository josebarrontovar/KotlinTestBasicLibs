package com.example.kotlintest_lib.data.model.AuthResponseDto

import com.example.kotlintest_lib.domain.model.AuthTokens
import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
) {
    fun toDomain(): AuthTokens {
        return AuthTokens(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}