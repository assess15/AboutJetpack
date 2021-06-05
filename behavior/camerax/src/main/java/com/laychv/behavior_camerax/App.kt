package com.laychv.behavior_camerax

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import com.laychv.lib_base.Logger

class App : Application(), CameraXConfig.Provider {

    override fun onCreate() {
        super.onCreate()
        Logger.init("camerax")
    }

    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }
}