package com.laychv.arch_paging3.paging3_room.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.laychv.arch_paging3.paging3_room.base.BaseViewModel
import com.laychv.arch_paging3.paging3_room.data.model.ArticleList
import com.laychv.arch_paging3.paging3_room.data.repository.ArticleRepository

class PagingViewModel(private val repository: ArticleRepository) : BaseViewModel(repository) {

    private fun getArticleData() = repository.getArticleData().cachedIn(viewModelScope).asLiveData()

    fun getArticles(
        owner: LifecycleOwner,
        observer: Observer<PagingData<ArticleList>>
    ) {
        getArticleData().observe(owner, observer)
    }

//    fun getArticles(
//        owner: LifecycleOwner,
//        observer: Observer<PagingData<ArticleList>>
//    ) {
//        getArticle().observe(owner,observer)
//    }
//
//    private fun getArticle() = repository.getArticle().cachedIn(viewModelScope).asLiveData()
}