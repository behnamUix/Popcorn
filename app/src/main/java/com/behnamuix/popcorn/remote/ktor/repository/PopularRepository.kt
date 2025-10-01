package com.behnamuix.popcorn.remote.ktor.repository

import com.behnamuix.popcorn.remote.ktor.model.PopularMovie
import com.behnamuix.popcorn.remote.ktor.model.PopularMovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PopularRepository(
    private val client: HttpClient
){
    suspend fun getPopular(): List<PopularMovie>{
       try {
           val response: PopularMovieResponse=client.get(
               "https://api.themoviedb.org/3/movie/popular?api_key=dcd0eaabd3f874905d95b1479ab2c685&language=fa&page=2"
           ).body()
           return response.results
       }catch (e:Exception){
           e.printStackTrace()
           return emptyList()
       }
    }
}