package com.vaulert.arch_datastore

import android.app.Application
import com.vaulert.lib_base.Logger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.init("ds")
    }

}