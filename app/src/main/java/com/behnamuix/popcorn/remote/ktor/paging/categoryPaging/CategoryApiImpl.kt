package com.behnamuix.popcorn.remote.ktor.paging.categoryPaging

import com.behnamuix.popcorn.remote.ktor.model.MovieByGenreResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface CategoryApi{
    suspend fun getMovieByGenre(id:Int,page:Int):MovieByGenreResponse

}
class CategoryApiImpl(
    var client:HttpClient
):CategoryApi{
    override suspend fun getMovieByGenre(id: Int, page: Int): MovieByGenreResponse {
        return client.get(
                "https://api.themoviedb.org/3/discover/movie"
            ){
            parameter("api_key", "dcd0eaabd3f874905d95b1479ab2c685")
            parameter("with_genres", id)
            parameter("language", "fa")
            parameter("page", page)
        }.body()

    }

}