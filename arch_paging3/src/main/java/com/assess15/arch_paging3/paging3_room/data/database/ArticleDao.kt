package com.assess15.arch_paging3.paging3_room.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assess15.arch_paging3.paging3_room.data.model.ArticleList

//@Dao
//interface ArticleDao {
//
//    @Query("DELETE FROM article")
//    suspend fun clearAll()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(article: List<ArticleList>)
//
//    @Query("SELECT * FROM article")
//    fun queryArticle(): PagingSource<Int, ArticleList>
//}