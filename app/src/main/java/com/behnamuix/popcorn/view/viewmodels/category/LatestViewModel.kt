package com.behnamuix.popcorn.view.viewmodels.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.remote.ktor.model.LatestMovie
import com.behnamuix.popcorn.remote.ktor.repository.LatestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LatestViewModel(val repo: LatestRepository) : ViewModel() {
    //in dige list nist taki ye item hastesh

    private val _latest = MutableStateFlow<LatestMovie?>(null)
    val latest: StateFlow<LatestMovie?> = _latest
    init {
        loadLatest()
    }
    private fun loadLatest() {
        viewModelScope.launch {
            runCatching { repo.getLatest() }
                .onSuccess {
                    _latest.value=it
                    Log.d("debugX", "Loaded latest: $it")

                }
                .onFailure {e->
                    _latest.value = null
                    Log.e("debugX", "Error loading latest", e)

                }

        }
    }

}