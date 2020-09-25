package com.vaulert.arch_room.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vaulert.arch_room.demo.db.dao.StudentDao
import com.vaulert.arch_room.demo.db.entity.StudentEntity

@Database(
    entities = [StudentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}