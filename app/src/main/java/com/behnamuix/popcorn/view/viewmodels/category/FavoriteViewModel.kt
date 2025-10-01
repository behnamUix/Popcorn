package com.behnamuix.popcorn.view.viewmodels.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.popcorn.database.room.entity.FavoritesMovieModel
import com.behnamuix.popcorn.database.room.repository.FavoriteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repo: FavoriteMovieRepository) : ViewModel() {

    var movies by mutableStateOf<List<FavoritesMovieModel>>(emptyList())

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            movies = repo.getFavoriteMovie()
        }
    }

    fun addToFavorites(movie: FavoritesMovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(movie)
            loadFavorites()
        }
    }

    fun removeFromFavorites(movie: FavoritesMovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(movie)
            loadFavorites()
        }
    }


}