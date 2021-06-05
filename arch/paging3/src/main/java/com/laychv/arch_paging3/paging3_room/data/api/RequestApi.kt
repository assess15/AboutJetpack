package com.laychv.arch_paging3.paging3_room.data.api

import com.laychv.arch_paging3.paging3_room.data.model.Article
import com.laychv.arch_paging3.paging3_room.data.model.BaseResp
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {
    @GET("article/list/{page}/json")
    suspend fun getData(@Path("page") page: Int): BaseResp<Article>
}