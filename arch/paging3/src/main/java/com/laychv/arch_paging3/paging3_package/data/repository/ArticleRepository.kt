package com.laychv.arch_paging3.paging3_package.data.repository

import com.laychv.arch_paging3.paging3_package.data.api.RequestApi
import com.laychv.arch_paging3.paging3_package.data.model.DataList

class ArticleRepository(private val api: RequestApi) {

    suspend fun getArticleData(page: Int): List<DataList> {
        return api.getData(page)
    }
}