package com.laychv.arch_datastore

import android.app.Application
import com.laychv.lib_base.Logger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.init("ds")
    }

}