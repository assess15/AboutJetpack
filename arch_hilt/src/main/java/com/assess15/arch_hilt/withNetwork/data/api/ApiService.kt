package com.assess15.arch_hilt.withNetwork.data.api

import com.assess15.arch_hilt.withNetwork.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUser():Response<List<User>>

    @GET
    suspend fun ffss():Response<String>
}