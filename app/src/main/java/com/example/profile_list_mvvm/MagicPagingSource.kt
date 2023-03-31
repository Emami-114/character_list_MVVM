package com.example.profile_list_mvvm

import androidx.paging.PagingSource
import androidx.paging.PagingState


/**
class MagicPagingSource(private val apiService: ApiService) : PagingSource<Int, CharacterList.Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterList.Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.fetchCaracter(currentPage)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterList.Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
        if (state.anchorPosition != null) {
            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
            if (anchorPage?.prevKey != null) {
                return anchorPage.prevKey!!.plus(1)
            } else if (anchorPage?.nextKey != null
            ) {
                return anchorPage.nextKey!!.minus(1)
            }
        }
        else{
            return null
        }
    }

}
*/
