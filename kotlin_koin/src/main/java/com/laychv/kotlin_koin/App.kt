package com.laychv.kotlin_koin

import android.app.Application
import com.laychv.kotlin_koin.demo.appModule
import com.laychv.kotlin_koin.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@App)
            modules(appModule)
            modules(networkModule)
        }
    }
}