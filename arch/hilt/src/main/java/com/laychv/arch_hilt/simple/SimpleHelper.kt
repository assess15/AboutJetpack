package com.laychv.arch_hilt.simple

import android.content.Context
import android.widget.TextView
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimpleHelper @Inject constructor(@ApplicationContext private val context: Context) {
    fun getTV(): TextView {
        return TextView(context)
    }
}