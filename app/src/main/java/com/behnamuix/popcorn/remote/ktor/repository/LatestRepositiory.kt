package com.behnamuix.popcorn.remote.ktor.repository

import android.util.Log
import com.behnamuix.popcorn.remote.ktor.model.LatestMovie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.request.get

class LatestRepository(
    private val client: HttpClient
) {

    suspend fun getLatest(): LatestMovie? {
        return try {
            client.get(
                "https://api.themoviedb.org/3/movie/latest?api_key=dcd0eaabd3f874905d95b1479ab2c685"
            ).body()
        } catch (e: ConnectTimeoutException) {
            Log.e("LatestRepository", "Connect timeout", e)
            null
        } catch (e: Exception) {
            Log.e("LatestRepository", "Network error", e)
            null
        }
    }
}
