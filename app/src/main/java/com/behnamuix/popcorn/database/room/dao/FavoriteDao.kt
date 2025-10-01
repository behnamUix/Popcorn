package com.behnamuix.popcorn.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.behnamuix.popcorn.database.room.entity.FavoritesMovieModel

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: FavoritesMovieModel)

    @Query("SELECT * FROM favorites")
    fun getAllFavoriteMovie(): List<FavoritesMovieModel>

    @Delete
    suspend fun deleteFavoriteMovie(movie: FavoritesMovieModel)

}