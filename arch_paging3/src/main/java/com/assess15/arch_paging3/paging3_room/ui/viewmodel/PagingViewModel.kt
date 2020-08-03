package com.assess15.arch_paging3.paging3_room.ui.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.assess15.arch_paging3.paging3_room.base.BaseViewModel
import com.assess15.arch_paging3.paging3_room.data.repository.ArticleRepository

class PagingViewModel(private val repository: ArticleRepository) : BaseViewModel(repository) {

    fun getArticleData() = repository.getArticleData().cachedIn(viewModelScope).asLiveData()
}