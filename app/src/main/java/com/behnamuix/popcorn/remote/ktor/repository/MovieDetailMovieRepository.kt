package com.behnamuix.popcorn.remote.ktor.repository

import com.behnamuix.popcorn.remote.ktor.model.MovieDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieDetailMovieRepository(
    private val client:HttpClient
) {
    suspend fun getDetailMovie(imdb_id:Int): MovieDetails {
        return client.get(
            "https://api.themoviedb.org/3/movie/$imdb_id?api_key=dcd0eaabd3f874905d95b1479ab2c685&language=fa"
        ).body()
    }

}