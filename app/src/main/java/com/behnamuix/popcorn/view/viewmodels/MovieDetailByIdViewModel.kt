package com.behnamuix.popcorn.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.remote.ktor.model.MovieDetails
import com.behnamuix.popcorn.remote.ktor.repository.MovieDetailMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailByIdViewModel(
    private val repo: MovieDetailMovieRepository
) : ViewModel() {
    private val _deatilMovie = MutableStateFlow<MovieDetails?>(null)
    val deatilMovie: StateFlow<MovieDetails?> = _deatilMovie
     fun loadFullDetailByImdb(imdbId:Int) {
        viewModelScope.launch {
            runCatching { repo.getDetailMovie(imdbId) }
                .onSuccess {
                    _deatilMovie.value=it
                    Log.d("debugX", "Loaded latest: $it")

                }
                .onFailure {e->
                    _deatilMovie.value = null
                    Log.e("debugX", "Error loading latest", e)

                }

        }
    }

}