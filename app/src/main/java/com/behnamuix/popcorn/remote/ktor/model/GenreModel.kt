package com.behnamuix.popcorn.remote.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponseModel(
    val genres: List<Genre>
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)
