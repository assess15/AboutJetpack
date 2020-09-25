package com.vaulert.kotlin_koin.network

open class BaseResponse<T>(
    val `data`: List<T>,
    val errorCode: Int,
    val errorMsg: String
)