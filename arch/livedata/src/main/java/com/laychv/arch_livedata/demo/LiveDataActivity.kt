package com.laychv.arch_livedata.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveDataUtil.getMLD().observe(this, Observer {
            Log.d("aaas", "收到参数:$it");
        })

        val mld = MutableLiveData<String>()
        mld.observe(this, Observer<String> {
            Log.d("aaas", "onChanged:$it");
        })
        mld.postValue("Android进阶三部曲")

    }
}