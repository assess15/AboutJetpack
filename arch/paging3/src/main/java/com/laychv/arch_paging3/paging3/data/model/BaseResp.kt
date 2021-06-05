package com.laychv.arch_paging3.paging3.data.model

class BaseResp<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)