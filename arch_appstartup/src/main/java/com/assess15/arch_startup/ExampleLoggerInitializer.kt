package com.assess15.arch_startup

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManager

class ExampleLoggerInitializer : Initializer<ExampleLogger> {

    override fun create(context: Context): ExampleLogger {
        // WorkManager.getInstance() is non-null only after
        // WorkManager is initialized.
        return ExampleLogger(WorkManager.getInstance(context))
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // Defines a dependency on WorkManagerInitializer so it can be
        // initialized after WorkManager is initialized.
        return listOf(WorkManagerInitializer::class.java)
    }
}