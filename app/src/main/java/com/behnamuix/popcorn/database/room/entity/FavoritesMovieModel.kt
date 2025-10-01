package com.behnamuix.popcorn.database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesMovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image:  String? = null,
    val imdbId:  String? = null,
    val title: String,
    val overview: String,
    val rate: Double,
    val genre: List<Int>
)