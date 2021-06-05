package com.laychv.arch_paging3.paging3_package

//定义网络请求状态(密封类扩展性更好)
sealed class NetworkState

object LoadState : NetworkState()

object SuccessState : NetworkState()

class ErrorState(val message: String?) : NetworkState()