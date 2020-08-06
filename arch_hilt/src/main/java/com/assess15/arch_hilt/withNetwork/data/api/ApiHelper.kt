package com.assess15.arch_hilt.withNetwork.data.api

import com.assess15.arch_hilt.withNetwork.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUser(): Response<List<User>>
}