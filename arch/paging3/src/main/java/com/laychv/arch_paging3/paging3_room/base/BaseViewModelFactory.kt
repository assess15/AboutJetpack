package com.laychv.arch_paging3.paging3_room.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
open class BaseViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return onCreate() as T
    }

    open fun onCreate(): BaseViewModel {
        return BaseViewModel(repository)
    }

}