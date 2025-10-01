package com.behnamuix.popcorn.remote.ktor.paging.searchPaging

import com.behnamuix.popcorn.remote.ktor.model.SearchMovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface SearchApi {
    suspend fun searchMovie(query: String, page: Int): SearchMovieResponse
}

class SearchApiImpl(
    private val client: HttpClient
) : SearchApi {
    override suspend fun searchMovie(query: String, page: Int): SearchMovieResponse {
        return client.get(
            "https://api.themoviedb.org/3/search/movie"

        ) {
            parameter("api_key", "dcd0eaabd3f874905d95b1479ab2c685")
            parameter("query", query)
            parameter("language", "fa")
            parameter("page", page)
        }.body()
    }


}