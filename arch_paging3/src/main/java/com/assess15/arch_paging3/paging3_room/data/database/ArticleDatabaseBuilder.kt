package com.assess15.arch_paging3.paging3_room.data.database

import android.content.Context
import androidx.room.Room

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