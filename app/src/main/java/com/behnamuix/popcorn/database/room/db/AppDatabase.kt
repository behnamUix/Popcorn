package com.behnamuix.popcorn.database.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.behnamuix.popcorn.database.room.converter.Converter
import com.behnamuix.popcorn.database.room.dao.FavoriteMovieDao
import com.behnamuix.popcorn.database.room.entity.FavoritesMovieModel


@Database(
    entities = [FavoritesMovieModel::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}