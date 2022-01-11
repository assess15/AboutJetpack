package com.laychv.arch_lifecycle

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LocationObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startLocation() {
        Log.d("aaa", "开始定位.....")
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("aa", "延迟5秒,定位....")
        }, 5000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopLocation() {
        Log.d("aaa", "停止定位.....")
    }

}