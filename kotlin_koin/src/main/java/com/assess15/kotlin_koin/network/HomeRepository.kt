package com.assess15.kotlin_koin.network

class HomeRepository(private val api: HomeApi) {

    suspend fun getTree(): BaseResponse<TreeBean> = api.getTree()
}