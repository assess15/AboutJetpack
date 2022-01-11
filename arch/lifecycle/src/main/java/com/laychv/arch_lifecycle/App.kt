package com.laychv.arch_lifecycle

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycle())
    }

    inner class AppLifecycle : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun appStart() {
            Log.d("a", "app start")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun appDestroy() {
            Log.d("a", "app destroy!!!")
        }
    }
}