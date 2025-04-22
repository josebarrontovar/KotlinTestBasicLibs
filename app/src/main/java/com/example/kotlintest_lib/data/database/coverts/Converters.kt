package com.example.kotlintest_lib.data.database.coverts

import androidx.room.TypeConverter
import com.example.kotlintest_lib.data.database.entities.CategoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromCategory(category: CategoryEntity): String {
        return gson.toJson(category)
    }

    @TypeConverter
    fun toCategory(categoryString: String): CategoryEntity {
        return gson.fromJson(categoryString, CategoryEntity::class.java)
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}