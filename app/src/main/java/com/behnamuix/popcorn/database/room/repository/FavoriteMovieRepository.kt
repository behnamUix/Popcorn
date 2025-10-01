package com.behnamuix.popcorn.database.room.repository

import com.behnamuix.popcorn.database.room.dao.FavoriteMovieDao
import com.behnamuix.popcorn.database.room.entity.FavoritesMovieModel

class FavoriteMovieRepository(private val dao: FavoriteMovieDao) {

    suspend fun getFavoriteMovie()=dao.getAllFavoriteMovie()

    suspend fun insert(movie: FavoritesMovieModel) = dao.insertMovie(movie)

    suspend fun delete(movie: FavoritesMovieModel) = dao.deleteFavoriteMovie(movie)
}