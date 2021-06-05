package com.laychv.arch_paging3.paging3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.laychv.arch_paging3.paging3.data.repository.ArticleRepository

class PagingViewModel : ViewModel() {

    private val repository: ArticleRepository by lazy { ArticleRepository() }

    fun getArticleData() = repository.getArticleData().cachedIn(viewModelScope).asLiveData()

    fun getArticleList() = repository.getSearchResultStream().cachedIn(viewModelScope).asLiveData()
}