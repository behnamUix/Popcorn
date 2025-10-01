package com.behnamuix.popcorn.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.remote.ktor.model.CastMember
import com.behnamuix.popcorn.remote.ktor.model.CrewMember
import com.behnamuix.popcorn.remote.ktor.model.TrailerResponse
import com.behnamuix.popcorn.remote.ktor.model.TrailerResult
import com.behnamuix.popcorn.remote.ktor.repository.ActorRepository
import com.behnamuix.popcorn.remote.ktor.repository.TrailerMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrailerMovieViewModel(
    private val repo: TrailerMovieRepository
) : ViewModel() {
    private val _trailerMovie = MutableStateFlow<List<TrailerResult>>(emptyList())
    val trailerMovie: StateFlow<List<TrailerResult>> = _trailerMovie
    fun getTrailer(imdbId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { repo.getTrailer(imdbId) }
                .onSuccess {
                    Log.d("debugX","cast loaded: ${it.size} members")
                    _trailerMovie.value = it }
                .onFailure {
                    Log.d("debugX","error in loading cast${it.message.toString()}")
                }
        }


    }



}