package com.example.profile_list_mvvm


//class MagicPagingSource(private val apiService: ApiService) : PagingSource<Int, Charakter>() {
//    override fun getRefreshKey(state: PagingState<Int, Charakter>): Int? {
//        return null
//    }

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Charakter> {
//        return try {
//            val currentPage = params.key ?: 1
//            val response = apiService.fetchCaracter(currentPage)
//            val data = response
//        } catch (ex: Exception) {
//            LoadResult.Error(ex)
//        }
//    }
//}