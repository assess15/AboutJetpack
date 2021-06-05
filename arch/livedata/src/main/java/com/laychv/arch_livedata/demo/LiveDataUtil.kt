package com.laychv.arch_livedata.demo

import androidx.lifecycle.MutableLiveData

object LiveDataUtil {

    private var mld: MutableLiveData<String>? = null

    init {
        mld = MutableLiveData()
    }

    fun getMLD(): MutableLiveData<String> {
        return mld!!
    }

}