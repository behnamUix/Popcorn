package com.behnamuix.popcorn.remote.ktor.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
data class PopularMovieResponse(
    val page: Int,
    val results: List<PopularMovie>,
    val total_pages: Int,
    val total_results: Int
)

@Immutable
@Serializable
data class PopularMovie(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)