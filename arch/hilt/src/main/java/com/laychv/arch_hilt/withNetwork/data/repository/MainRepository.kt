package com.laychv.arch_hilt.withNetwork.data.repository

import com.laychv.arch_hilt.withNetwork.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUser() = apiHelper.getUser()
}