package com.laychv.arch_paging3.paging3_room.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.laychv.arch_paging3.paging3_room.base.BaseRepository
import com.laychv.arch_paging3.paging3_room.comm.RetrofitClient
import com.laychv.arch_paging3.paging3_room.data.model.ArticleList

//class ArticleRepository(private val database: ArticleDatabase) : BaseRepository() {
class ArticleRepository : BaseRepository() {

    fun getArticleData() = Pager(config) {
        ArticleDataSource()
    }.flow

    inner class ArticleDataSource : PagingSource<Int, ArticleList>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleList> {
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

        override fun getRefreshKey(state: PagingState<Int, ArticleList>): Int? {
            TODO("Not yet implemented")
        }
    }

    private val config: PagingConfig
        get() {
            return PagingConfig(
                pageSize = 20
            )
        }
//
//    fun getArticle(): Flow<PagingData<ArticleList>> {
//        val pagingSourceFactory = { database.articleDao().queryArticle() }
//        return Pager(
//            config,
//            remoteMediator = ArticleRemoteMonitor(database),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }
}