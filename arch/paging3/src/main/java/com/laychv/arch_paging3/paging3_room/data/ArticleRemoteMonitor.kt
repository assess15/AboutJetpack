//package com.laychv.arch_paging3.paging3_room.data
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.laychv.arch_paging3.paging3_room.comm.RetrofitClient
//import com.laychv.arch_paging3.paging3_room.data.database.ArticleDatabase
//import com.laychv.arch_paging3.paging3_room.data.model.ArticleList
//import retrofit2.HttpException
//import java.io.IOException
//
//@OptIn(ExperimentalPagingApi::class)
//class ArticleRemoteMonitor(private val database: ArticleDatabase) : RemoteMediator<Int, ArticleList>() {
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ArticleList>
//    ): MediatorResult {
//
//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                0
//            }
//            LoadType.PREPEND -> {
//                0
//            }
//            LoadType.APPEND -> {
//                1
//            }
//
//        }
//
//        try {
//            val apiResponse = RetrofitClient.reqApi.getData(page)
//
//            val repos = apiResponse.data.datas
//            val endOfPaginationReached = repos.isEmpty()
//            database.withTransaction {
//                // clear all tables in the database
//                if (loadType == LoadType.REFRESH) {
//                    database.articleDao().clearAll()
//                }
//                database.articleDao().insertAll(repos)
//            }
//            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (exception: IOException) {
//            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
//        }
//
//    }
//
//}