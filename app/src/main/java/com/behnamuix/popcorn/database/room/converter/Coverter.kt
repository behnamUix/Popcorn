package com.behnamuix.popcorn.database.room.converter

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromGenreList(genres: List<Int>): String {
        return genres.joinToString(",") // لیست اعداد رو به رشته تبدیل می‌کنه
    }

    @TypeConverter
    fun toGenreList(data: String): List<Int> {
        return if (data.isEmpty()) emptyList() else data.split(",").map { it.toInt() }
    }
}
