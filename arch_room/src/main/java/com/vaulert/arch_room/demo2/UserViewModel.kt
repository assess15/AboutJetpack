package com.vaulert.arch_room.demo2

import androidx.lifecycle.*

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    //    private val data = MutableLiveData<List<User>>()
    private val data: MutableLiveData<List<User>> by lazy { MutableLiveData<List<User>>() }

    val dataLiveData = Transformations.switchMap(data) {
        liveData<List<User>> {
            val queryUser = repository.queryUser()
            emit(queryUser)
        }
    }

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    suspend fun clearUser() {
        repository.clearUser()
    }

    suspend fun queryUser(): List<User> {
        return repository.queryUser()
    }

    fun getLiveDataUser() :LiveData<List<User>>{
        return repository.getLiveDataUser()
    }
}