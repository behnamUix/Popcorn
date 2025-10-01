package com.behnamuix.popcorn.remote.ktor.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
data class SearchMovieResponse(
    val page: Int,
    val results: List<SearchMovie>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class SearchMovie(
    val adult: Boolean? = false,
    val backdrop_path: String? = null,
    val genre_ids: List<Int>? = emptyList(), // ممکنه وجود نداشته باشه
    val id: Int,
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = null,
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)
