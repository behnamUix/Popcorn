package com.behnamuix.popcorn.remote.ktor.repository

import com.behnamuix.popcorn.remote.ktor.model.Genre
import com.behnamuix.popcorn.remote.ktor.model.GenreResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GenreRepository(
    private val client: HttpClient
) {
    suspend fun getGenre(): List<Genre>{
        return try {
            val response: GenreResponseModel=client.get(
                "https://api.themoviedb.org/3/genre/movie/list?api_key=dcd0eaabd3f874905d95b1479ab2c685"        ).body()
          response.genres
        }catch (e:Exception){
            e.printStackTrace()
            return emptyList()

        }
    }
}