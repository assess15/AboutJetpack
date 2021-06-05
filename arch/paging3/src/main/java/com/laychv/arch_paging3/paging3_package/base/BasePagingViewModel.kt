package com.laychv.arch_paging3.paging3_package.base

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*

abstract class BasePagingViewModel<M : Any> : BaseViewModel() {

    val pagedList by lazy {
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            PageDataSource()
        }.flow.asLiveData().cachedIn(viewModelScope)
    }

    inner class PageDataSource : PagingSource<Int, M>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, M> {
            return try {
                val page = params.key ?: 1
                val list = getDataList(page)
                LoadResult.Page(
                    data = list,
                    prevKey = null,
                    nextKey = if (list.isEmpty()) null else page + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, M>): Int? {
            TODO("Not yet implemented")
        }
    }

    abstract suspend fun getDataList(page: Int): List<M>
}