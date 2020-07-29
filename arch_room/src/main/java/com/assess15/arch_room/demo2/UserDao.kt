package com.assess15.arch_room.demo2

import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from user")
    fun queryUser(): List<User>

    @Update
    fun updateUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Delete
    fun deleteUser(user: User)

}