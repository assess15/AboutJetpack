package com.laychv.arch_startup

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.startup.Initializer
import com.laychv.lib_base.BuildConfig
import timber.log.Timber

class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) {
            return
        }
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
        )
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        Timber.plant(Timber.DebugTree())
        return
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}