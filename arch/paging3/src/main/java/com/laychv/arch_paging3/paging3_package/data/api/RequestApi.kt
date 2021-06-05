package com.laychv.arch_paging3.paging3_package.data.api

import com.laychv.arch_paging3.paging3_package.data.model.DataList
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {
    @GET("article/list/{page}/json")
    suspend fun getData(@Path("page") page: Int): List<DataList>
}