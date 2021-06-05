package com.laychv.kotlin_koin.network

import retrofit2.http.GET

interface HomeApi {

    @GET("tree/json")
    suspend fun getTree(): BaseResponse<TreeBean>
}