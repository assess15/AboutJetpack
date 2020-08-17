package com.assess15.kotlin_koin.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    val data: MutableLiveData<BaseResponse<TreeBean>> = MutableLiveData()

    fun getTree() {
        viewModelScope.launch {
            val tree = repository.getTree()
            data.postValue(tree)
        }
    }
}