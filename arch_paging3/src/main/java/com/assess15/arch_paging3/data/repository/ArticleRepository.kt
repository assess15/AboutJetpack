package com.assess15.arch_paging3.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.assess15.arch_paging3.data.model.DataList
import com.assess15.arch_paging3.utils.ClientUtil
import kotlinx.coroutines.flow.Flow

class ArticleRepository {

    fun getArticleData() = Pager(config) {
        ArticleDataSource()
    }.flow

    inner class ArticleDataSource : PagingSource<Int, DataList>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataList> {
            return try {
                val page = params.key ?: 0
                val result = ClientUtil.reqApi.getData(page)
                Log.d("aa", result.data.datas.toString())
                LoadResult.Page(
                    data = result.data.datas,
//                    prevKey = if (page == 0) null else page - 1,
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
                // 每页显示的数据的大小
                pageSize = 20
                // 开启占位符
//                enablePlaceholders = false
                // 预刷新的距离，距离最后一个 item 多远时加载数据
//                prefetchDistance = 3,
                // 初始化加载数量，默认为 pageSize * 3
//                initialLoadSize = 4
            )
        }

    fun getSearchResultStream(): Flow<PagingData<DataList>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticleDataSource() }
        ).flow
    }
}