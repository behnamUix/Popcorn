package com.behnamuix.popcorn.remote.ktor.repository

import com.behnamuix.popcorn.remote.ktor.model.ActorMovieModel
import com.behnamuix.popcorn.remote.ktor.model.CastMember
import com.behnamuix.popcorn.remote.ktor.model.CrewMember
import com.behnamuix.popcorn.remote.ktor.model.TrailerResponse
import com.behnamuix.popcorn.remote.ktor.model.TrailerResult

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TrailerMovieRepository(
    private val client: HttpClient
) {
    suspend fun getTrailer(imdbId: Int): List<TrailerResult> {
        return try {
            val response: TrailerResponse = client.get(
                "https://api.themoviedb.org/3/movie/$imdbId/videos?api_key=dcd0eaabd3f874905d95b1479ab2c685"
            ).body()
            response.results
        } catch (e: Exception) {
            e.printStackTrace()

            emptyList()
        }
    }

}
