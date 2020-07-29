package com.assess15.arch_paging3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.assess15.arch_paging3.data.repository.ArticleRepository

class PagingViewModel : ViewModel() {

    private val repository: ArticleRepository by lazy { ArticleRepository() }

    fun getArticleData() = repository.getArticleData().asLiveData()
}