package com.assess15.arch_room.demo.db

import android.content.Context
import androidx.room.Room

object StudentDatabaseBuilder {

    @Volatile
    private var INSTANCE: StudentDatabase? = null

    fun getInstance(context: Context): StudentDatabase = INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
    }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            StudentDatabase::class.java,
            "student"
        ).build()
}