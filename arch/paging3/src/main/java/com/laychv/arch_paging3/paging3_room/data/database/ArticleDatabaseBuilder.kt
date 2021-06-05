package com.laychv.arch_paging3.paging3_room.data.database

//
//object ArticleDatabaseBuilder {
//
//    @Volatile
//    private var instance: ArticleDatabase? = null
//
//    fun getInstance(context: Context): ArticleDatabase {
//        return instance ?: synchronized(this) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//    }
//
//    private fun buildDatabase(context: Context): ArticleDatabase {
//        return Room.databaseBuilder(context, ArticleDatabase::class.java, "article").build()
//    }
//}