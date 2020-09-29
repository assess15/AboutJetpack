package com.vaulert.arch_room.demo2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 6, exportSchema = true)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase = INSTANCE
            ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, UsersDatabase::class.java, "user.db")
                // 更新完表后,可以删除
                .addMigrations(MIGRATION_5_6)
                // 破坏性迁移,先删除以前数据库,再新建
//                .fallbackToDestructiveMigration()
                .build()
    }
}