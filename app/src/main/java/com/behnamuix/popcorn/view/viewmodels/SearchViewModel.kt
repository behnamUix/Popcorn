package com.behnamuix.popcorn.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.Query
import com.behnamuix.popcorn.remote.ktor.model.SearchMovie
import com.behnamuix.popcorn.remote.ktor.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class SearchViewModel(private val repository: SearchRepository):ViewModel(){
    fun searchMovie(query: String): Flow<PagingData<SearchMovie>>{
        return repository.searchMovie(query).cachedIn(viewModelScope)
    }
}


