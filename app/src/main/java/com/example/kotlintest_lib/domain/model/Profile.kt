package com.example.kotlintest_lib.domain.model

import com.google.gson.annotations.SerializedName

data class Profile(
    val id: Int,
    val email: String,
    @Transient
    @SerializedName("password")
    val ignoredPassword: String = "",
    val name: String,
    val role: String,
    val avatar: String,
    val creationAt: String,
    val updatedAt: String
)