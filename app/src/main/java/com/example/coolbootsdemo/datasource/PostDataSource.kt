package com.example.coolbootsdemo.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.coolbootsdemo.const.AppConstant
import com.example.coolbootsdemo.model.Data
import com.example.coolbootsdemo.network.RetrofitService

class PostDataSource(private val retrofitService: RetrofitService): PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
       try {
           val currentLoadingPageKey = params.key ?:1
           val response = retrofitService.getAllQuestions(AppConstant.USER_TOKEN,AppConstant.HANDSHAKE,16,currentLoadingPageKey)
           val responseData = mutableListOf<Data>()
           val data = response.body()?.response?.data ?: emptyList()
           responseData.addAll(data)

           val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
           return LoadResult.Page(
               data = responseData,
               prevKey = prevKey,
               nextKey = currentLoadingPageKey.plus(1)
           )
       } catch (e: Exception) {
           return LoadResult.Error(e)
       }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        TODO("Not yet implemented")
    }
}