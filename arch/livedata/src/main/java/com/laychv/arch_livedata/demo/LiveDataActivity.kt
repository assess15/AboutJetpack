package com.laychv.arch_livedata.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData

class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveDataUtil.getMLD().observe(this, {
            Log.d("aaas", "收到参数:$it");
        })

        val mld = MutableLiveData<String>()
        mld.observe(this, {
            Log.d("aaas", "onChanged:$it");
        })
        mld.postValue("Android进阶三部曲")

    }
}