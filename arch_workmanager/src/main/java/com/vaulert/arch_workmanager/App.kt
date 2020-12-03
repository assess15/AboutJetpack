package com.vaulert.arch_workmanager

import android.app.Application
import android.util.Log
import androidx.work.Configuration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(MyWorker())
            .build()
    }
}