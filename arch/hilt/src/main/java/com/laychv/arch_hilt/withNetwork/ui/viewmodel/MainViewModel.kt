package com.laychv.arch_hilt.withNetwork.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laychv.arch_hilt.withNetwork.data.model.User
import com.laychv.arch_hilt.withNetwork.data.repository.MainRepository
import com.laychv.arch_hilt.withNetwork.utils.NetworkHelper
import com.laychv.arch_hilt.withNetwork.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val user = MutableLiveData<Resource<List<User>>>()

    val users: LiveData<Resource<List<User>>>
        get() = user

    fun getUser(): LiveData<Resource<List<User>>> {
        return user
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            user.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.getUser().let {
                    if (it.isSuccessful) {
                        user.postValue(Resource.success(it.body()))
                    } else {
                        user.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                user.postValue(Resource.error("error", null))
            }
        }
    }
}