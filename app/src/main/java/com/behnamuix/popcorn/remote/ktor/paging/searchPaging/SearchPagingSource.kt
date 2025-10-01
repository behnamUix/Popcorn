package com.behnamuix.popcorn.remote.ktor.paging.searchPaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.behnamuix.popcorn.remote.ktor.model.SearchMovie

class SearchPagingSource(
    private val api: SearchApi,
    private val query: String
):PagingSource<Int,SearchMovie>() {
    override fun getRefreshKey(state: PagingState<Int, SearchMovie>): Int? {
        return state.anchorPosition?.let { anchor->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchor)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovie> {
        return try {
            val page=params.key?:1
            val response=api.searchMovie(query,page)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page==1) null else page-1,
                nextKey = if(page<response.total_pages) page+1 else null
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}