package com.laychv.arch_hilt.withNetwork.data.api

import com.laychv.arch_hilt.withNetwork.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUser(): Response<List<User>>
}