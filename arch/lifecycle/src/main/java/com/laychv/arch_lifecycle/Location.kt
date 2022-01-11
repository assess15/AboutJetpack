package com.laychv.arch_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.OnLifecycleEvent

/**
 * 自定义
 */
class Location : LifecycleOwner {

    lateinit var registry: LifecycleRegistry

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        registry = LifecycleRegistry(this)
        registry.currentState = Lifecycle.State.CREATED
        Log.d("aaaa", "state start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        registry.currentState = Lifecycle.State.DESTROYED
        Log.d("aaaa", "state destroy")
    }

    override fun getLifecycle(): Lifecycle = registry
}