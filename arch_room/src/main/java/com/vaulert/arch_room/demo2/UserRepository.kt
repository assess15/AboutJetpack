package com.vaulert.arch_room.demo2

import androidx.lifecycle.LiveData

class UserRepository constructor(private val database: UsersDatabase) {

    suspend fun insertUser(user: User) {
        database.userDao().insertUser(user)
    }

    suspend fun clearUser() {
        database.userDao().clearUser()
    }

    suspend fun queryUser(): List<User> {
        return database.userDao().queryUser()
    }

    companion object {
        private var repository: UserRepository? = null

        fun getInstance(db: UsersDatabase): UserRepository {
            if (repository == null) {
                synchronized(UserRepository::class.java) {
                    if (repository == null) {
                        repository = UserRepository(db)
                    }
                }
            }
            return repository!!
        }
    }

    fun getLiveDataUser(): LiveData<List<User>> {
        return database.userDao().getLiveDataUsers()
    }
}