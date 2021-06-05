package com.laychv.arch_lifecycle

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LocationObserver(context: Context) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startLocation() {
        Log.d("aaa","开始定位.....")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopLocation() {
        Log.d("aaa","停止定位.....")
    }

}