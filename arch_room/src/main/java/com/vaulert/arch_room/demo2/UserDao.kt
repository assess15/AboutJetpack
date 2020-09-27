package com.vaulert.arch_room.demo2

import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from user")
    suspend fun queryUser(): List<User>

    @Update
    suspend fun updateUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun clearUser()
}