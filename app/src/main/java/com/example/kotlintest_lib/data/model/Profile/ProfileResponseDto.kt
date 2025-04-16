package com.example.kotlintest_lib.data.model.Profile

import com.example.kotlintest_lib.domain.model.Profile
import com.google.gson.annotations.SerializedName

data class ProfileResponseDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val ignoredPassword: String = "",

    @SerializedName("name")
    val name: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("avatar")
    val avatar: String,

    @SerializedName("creationAt")
    val creationAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
) {
    fun toDomain(): Profile {
        return Profile(
            id = id,
            email = email,
            password = ignoredPassword,
            name = name,
            role = role,
            avatar = avatar,
            creationAt = creationAt,
            updatedAt = updatedAt
        )
    }
}