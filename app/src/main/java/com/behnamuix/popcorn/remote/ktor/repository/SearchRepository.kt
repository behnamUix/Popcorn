package com.behnamuix.popcorn.remote.ktor.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.behnamuix.popcorn.remote.ktor.model.SearchMovie
import com.behnamuix.popcorn.remote.ktor.paging.searchPaging.SearchApi
import com.behnamuix.popcorn.remote.ktor.paging.searchPaging.SearchPagingSource
import kotlinx.coroutines.flow.Flow

class SearchRepository(private  val api: SearchApi) {
    fun searchMovie(query:String):Flow<PagingData<SearchMovie>>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { SearchPagingSource(api,query) }
        ).flow
    }
}