package com.behnamuix.popcorn.remote.ktor.paging.categoryPaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.behnamuix.popcorn.remote.ktor.model.MovieByGenre

class CategoryPagingSource(
    private val api:CategoryApi,
    private val id:Int
):PagingSource<Int,MovieByGenre>() {
    override fun getRefreshKey(state: PagingState<Int, MovieByGenre>): Int? {
        return state.anchorPosition?.let { anchor->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieByGenre> {
        return try {
            val page=params.key?:1
            val response=api.getMovieByGenre(id,page)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page==1)null else page-1,
                nextKey = if(page<response.totalPages) page+1 else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}