package com.laychv.arch_paging3.paging3_package.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laychv.arch_paging3.paging3_package.ErrorState
import com.laychv.arch_paging3.paging3_package.LoadState
import com.laychv.arch_paging3.paging3_package.NetworkState
import com.laychv.arch_paging3.paging3_package.SuccessState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

typealias LaunchBlock = suspend CoroutineScope.() -> Unit
typealias Cancel = (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<NetworkState>()//通用事件模型驱动(如：显示对话框、取消对话框、错误提示)

    fun launch(cancel: Cancel? = null, block: LaunchBlock) {//使用协程封装统一的网络请求处理
        viewModelScope.launch {
            //ViewModel自带的viewModelScope.launch,会在页面销毁的时候自动取消请求,有效封装内存泄露
            try {
                mStateLiveData.value = LoadState
                block()
                mStateLiveData.value = SuccessState
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> cancel?.invoke(e)
                    else -> mStateLiveData.value = ErrorState(e.message)
                }
            }
        }
    }
}