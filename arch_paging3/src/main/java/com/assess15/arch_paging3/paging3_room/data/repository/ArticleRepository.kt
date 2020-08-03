package com.assess15.arch_paging3.paging3_room.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.assess15.arch_paging3.paging3_room.base.BaseRepository
import com.assess15.arch_paging3.paging3_room.comm.RetrofitClient
import com.assess15.arch_paging3.paging3_room.data.model.DataList

class ArticleRepository : BaseRepository() {

    fun getArticleData() = Pager(config) {
        ArticleDataSource()
    }.flow

    inner class ArticleDataSource : PagingSource<Int, DataList>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataList> {
            return try {
                val page = params.key ?: 0
                val result = RetrofitClient.reqApi.getData(page)
                Log.d("aa", result.data.datas.toString())
                LoadResult.Page(
                    data = result.data.datas,
                    prevKey = null,
                    nextKey = if (result.data.curPage == result.data.pageCount) null else page + 1
                )
            } catch (e: Exception) {
                Log.d("aa", e.toString())
                LoadResult.Error(e)
            }
        }
    }

    private val config: PagingConfig
        get() {
            return PagingConfig(
                pageSize = 20
            )
        }
}