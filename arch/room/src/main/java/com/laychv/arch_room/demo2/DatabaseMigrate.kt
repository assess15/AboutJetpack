package com.laychv.arch_room.demo2

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `User` (`UserId` INTEGER, `UserName` STRING,`UserAge` STRING,`UserCars` LIST,PRIMARY KEY(`UserId`))")
    }
}

/** SQLite的ALTER TABLE命令非常局限，只支持重命名表以及添加新的字段。**/
/**
 * v2 -> v3
 * 1. 重命名表名: user -> User
 * 2. 修改了字段类型 : userId:String  -> userId : Int
 */
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 创建新表
//        database.execSQL("create table user (`UserId` NEXT NOT NULL,`UserName` NEXT,`UserAge` NEXT,`UserCars` LIST,PRIMARY KEY(`UserId`))")
        // 拷贝到新表中
//        database.execSQL("insert into user (UserId,UserName,UserAge,UserCars) select UserId,UserName,UserAge,UserCars from user")

        // 先删除旧表名
        database.execSQL("drop table user")
        // 再修改表名
        database.execSQL("alter table user rename to User")
    }
}

/**
 * 新增字段: sex
 */
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN UserSex TEXT NOT NULL")
    }
}

/**
 * 新增字段: Int类型字段
 * UserNation INTEGER NOT NULL DEFAULT 0
 * 需要添加 :
 * 数据类型: INTEGER
 * 不为空 : NOT NULL
 * 默认值 : DEFAULT
 */
val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN UserNation INTEGER NOT NULL DEFAULT 0")
    }
}

/**
 * 新增字段: String类型字段
 * UserFlag : String
 * NOT NULL DEFAULT 0
 */
val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN UserFlag TEXT NOT NULL DEFAULT 0")
    }
}