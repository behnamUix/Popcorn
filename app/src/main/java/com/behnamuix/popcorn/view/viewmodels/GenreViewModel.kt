package com.behnamuix.popcorn.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.remote.ktor.model.Genre
import com.behnamuix.popcorn.remote.ktor.repository.GenreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenreViewModel(
    private val repo: GenreRepository
) : ViewModel() {

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres

    init {
        loadGenres()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            runCatching { repo.getGenre() }
                .onSuccess {
                    _genres.value = it
                    Log.d("debugX", "Loaded genres: $it")
                }

                .onFailure {e->
                    _genres.value = emptyList()
                    Log.e("debugX", "Error loading genres", e)

                }
        }
    }
}
