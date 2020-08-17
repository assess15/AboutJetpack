package com.assess15.kotlin_koin

import android.app.Application
import com.assess15.kotlin_koin.demo.appModule
import com.assess15.kotlin_koin.network.networkModule
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