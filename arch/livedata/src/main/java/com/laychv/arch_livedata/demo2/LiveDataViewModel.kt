package com.laychv.arch_livedata.demo2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel() {

    val liveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }

    fun plus(count: Int) {
        liveData.value = liveData.value?.plus(count)
    }
}