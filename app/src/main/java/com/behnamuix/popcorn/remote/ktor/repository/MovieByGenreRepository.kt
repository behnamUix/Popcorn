package com.behnamuix.popcorn.remote.ktor.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.behnamuix.popcorn.remote.ktor.model.MovieByGenre
import com.behnamuix.popcorn.remote.ktor.model.MovieByGenreResponse
import com.behnamuix.popcorn.remote.ktor.paging.categoryPaging.CategoryApi
import com.behnamuix.popcorn.remote.ktor.paging.categoryPaging.CategoryPagingSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class MovieByGenreRepository(
    private val api: CategoryApi
) {
    fun categoryMovie(id:Int):Flow<PagingData<MovieByGenre>>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CategoryPagingSource(api,id)}
        ).flow

    }

}
