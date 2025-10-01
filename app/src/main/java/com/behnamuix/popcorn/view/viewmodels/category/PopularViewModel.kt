package com.behnamuix.popcorn.view.viewmodels.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.remote.ktor.model.PopularMovie
import com.behnamuix.popcorn.remote.ktor.repository.PopularRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PopularViewModel(val repo: PopularRepository): ViewModel() {
    private val _populars = MutableStateFlow<List<PopularMovie>>(emptyList())
    val populars: StateFlow<List<PopularMovie>> = _populars
    init {
        loadPopulars()
    }

    private fun loadPopulars() {
        viewModelScope.launch {
            //runCatching hamon try catch ghadimi hast!
            runCatching { repo.getPopular() }
                //dar sorat movafaghiat
                .onSuccess {
                    _populars.value = it
                    Log.d("debugX", "Loaded populars: $it")
                }
                //dar sorat khata
                .onFailure {e->
                    _populars.value = emptyList()
                    Log.e("debugX", "Error loading populars", e)

                }
        }
    }

}