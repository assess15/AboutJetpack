package com.assess15.arch_paging3.data.api

import com.assess15.arch_paging3.data.model.Article
import com.assess15.arch_paging3.data.model.BaseResp
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {
    @GET("article/list/{page}/json")
    suspend fun getData(@Path("page") page: Int): BaseResp<Article>
}