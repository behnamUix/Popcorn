package com.behnamuix.popcorn.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.remote.ktor.model.CastMember
import com.behnamuix.popcorn.remote.ktor.model.CrewMember
import com.behnamuix.popcorn.remote.ktor.repository.ActorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieActorByIdViewModel(
    private val repo: ActorRepository
) : ViewModel() {
    private val _castMovie = MutableStateFlow<List<CastMember>>(emptyList())
    val castMovie: StateFlow<List<CastMember>> = _castMovie
    private val _crewMovie = MutableStateFlow<List<CrewMember>>(emptyList())
    val crewMovie: StateFlow<List<CrewMember>> = _crewMovie
    fun loadCastDetail(imdbId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { repo.getCastById(imdbId) }
                .onSuccess {
                    Log.d("debugX","cast loaded: ${it.size} members")
                    _castMovie.value = it }
                .onFailure {
                    Log.d("debugX","error in loading cast${it.message.toString()}")
                }
        }

    }

    fun loadCrewDetail(imdbId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { repo.getCrewById(imdbId) }
                .onSuccess {
                    Log.d("debugX","crew loaded: ${it.size} members")

                    _crewMovie.value = it }
                .onFailure {
                    Log.d("debugX","error in loading crew${it.message.toString()}")
                }
        }
    }

}