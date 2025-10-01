package com.behnamuix.popcorn.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.behnamuix.popcorn.remote.ktor.model.MovieByGenre
import com.behnamuix.popcorn.remote.ktor.repository.MovieByGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieByGenreViewModel(
    private val repo: MovieByGenreRepository
) : ViewModel() {
    fun categoryMovie(id:Int): Flow<PagingData<MovieByGenre>> {
        return repo.categoryMovie(id).cachedIn(viewModelScope)
    }

}