package com.laychv.kotlin_koin.network

class HomeRepository(private val api: HomeApi) {

    suspend fun getTree(): BaseResponse<TreeBean> = api.getTree()
}