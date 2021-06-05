package com.laychv.arch_paging3.paging3_package.viewmodel

import com.laychv.arch_paging3.paging3_package.base.BasePagingViewModel
import com.laychv.arch_paging3.paging3_package.data.model.DataList
import com.laychv.arch_paging3.paging3_package.data.repository.ArticleRepository

class MainViewModel(private val repo: ArticleRepository) : BasePagingViewModel<DataList>() {

    override suspend fun getDataList(page: Int): List<DataList> {
        return repo.getArticleData(page)
    }
}