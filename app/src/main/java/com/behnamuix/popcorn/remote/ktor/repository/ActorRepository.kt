package com.behnamuix.popcorn.remote.ktor.repository

import androidx.compose.runtime.rememberCoroutineScope
import com.behnamuix.popcorn.remote.ktor.model.ActorMovieModel
import com.behnamuix.popcorn.remote.ktor.model.CastMember
import com.behnamuix.popcorn.remote.ktor.model.CrewMember

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.io.IOException

class ActorRepository(
    private val client: HttpClient
) {
    suspend fun getCastById(imdbId: Int): List<CastMember> {
        return try {
            val response: ActorMovieModel = client.get(
                "https://api.themoviedb.org/3/movie/$imdbId/credits?api_key=dcd0eaabd3f874905d95b1479ab2c685"
            ).body()
            response.cast
        } catch (e: Exception) {
            e.printStackTrace()

            emptyList()
        }
    }

    suspend fun getCrewById(imdbId: Int): List<CrewMember> {
        return try {
            val response: ActorMovieModel = client.get(
                "https://api.themoviedb.org/3/movie/$imdbId/credits?api_key=dcd0eaabd3f874905d95b1479ab2c685"
            ).body()
            response.crew
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
