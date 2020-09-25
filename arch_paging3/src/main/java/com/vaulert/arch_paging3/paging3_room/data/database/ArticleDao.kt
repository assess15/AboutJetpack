package com.vaulert.arch_paging3.paging3_room.data.database

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