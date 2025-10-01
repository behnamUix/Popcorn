package com.behnamuix.popcorn.view.viewmodels.category

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

class WarGenreViewModel(
    val repo: MovieByGenreRepository
) : ViewModel() {
    fun warMovie(id:Int): Flow<PagingData<MovieByGenre>> {
        return repo.categoryMovie(id).cachedIn(viewModelScope)
    }

}