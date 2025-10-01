package com.behnamuix.popcorn.koin

import androidx.room.Room
import com.behnamuix.popcorn.database.room.db.AppDatabase
import com.behnamuix.popcorn.database.room.repository.FavoriteMovieRepository
import com.behnamuix.popcorn.remote.ktor.paging.categoryPaging.CategoryApi
import com.behnamuix.popcorn.remote.ktor.paging.categoryPaging.CategoryApiImpl
import com.behnamuix.popcorn.remote.ktor.paging.searchPaging.SearchApi
import com.behnamuix.popcorn.remote.ktor.paging.searchPaging.SearchApiImpl
import com.behnamuix.popcorn.remote.ktor.repository.*
import com.behnamuix.popcorn.view.viewmodels.*
import com.behnamuix.popcorn.view.viewmodels.category.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "popcorn_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().favoriteMovieDao() }
    single { FavoriteMovieRepository(get()) }
    viewModel { FavoriteViewModel(get()) }
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true   // فیلدهای اضافی رو نادیده بگیره
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }
    //paging
    single<SearchApi> { SearchApiImpl(get()) }
    single<CategoryApi> { CategoryApiImpl(get()) }

    //repo
    single { GenreRepository(get()) }
    single { PopularRepository(get()) }
    single { LatestRepository(get()) }
    single { MovieByGenreRepository(get()) }
    single { MovieDetailMovieRepository(get()) }
    single { ActorRepository(get()) }
    single { SearchRepository(get()) }
    single { MovieByGenreRepository(get()) }
    single { TrailerMovieRepository(get()) }

    viewModel { GenreViewModel(get()) }
    viewModel { PopularViewModel(get()) }
    viewModel { LatestViewModel(get()) }
    viewModel { MovieByGenreViewModel(get()) }
    viewModel { MysteryGenreViewModel(get()) }
    viewModel { FearGenreViewModel(get()) }
    viewModel { LoveGenreViewModel(get()) }
    viewModel { ComedyGenreViewModel(get()) }
    viewModel { WarGenreViewModel(get()) }
    viewModel { MovieDetailByIdViewModel(get()) }
    viewModel { MovieActorByIdViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { TrailerMovieViewModel(get()) }
}
