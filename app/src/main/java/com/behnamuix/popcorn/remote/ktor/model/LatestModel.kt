package com.behnamuix.popcorn.remote.ktor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestMovie(
    val adult: Boolean? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    val belongs_to_collection: CollectionInfo? = null,
    val budget: Int? = null,
    val genres: List<Genres> = emptyList(),
    val homepage: String? = null,
    val id: Int? = null,
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("origin_country") val originCountry: List<String> = emptyList(),
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerialName("production_countries") val productionCountries: List<ProductionCountry> = emptyList(),
    @SerialName("release_date") val releaseDate: String? = null,
    val revenue: Long? = null,
    val runtime: Int? = null,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null
)

@Serializable
data class Genres(
    val id: Int? = null,
    val name: String? = null
)

@Serializable
data class ProductionCompany(
    val id: Int? = null,
    @SerialName("logo_path") val logoPath: String? = null,
    val name: String? = null,
    @SerialName("origin_country") val originCountry: String? = null
)

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1") val iso31661: String? = null,
    val name: String? = null
)

@Serializable
data class SpokenLanguage(
    @SerialName("english_name") val englishName: String? = null,
    @SerialName("iso_639_1") val iso6391: String? = null,
    val name: String? = null
)

@Serializable
data class CollectionInfo(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null
)
