package com.vaulert.arch_hilt.simple

import android.widget.TextView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class SimpleViewModel @ViewModelInject constructor(
    private val inj: SimpleInject,
    private val helper: SimpleHelper
) : ViewModel() {
    fun getINJString(): String = inj.doSomethings()
    fun tv(): TextView {
        return helper.getTV()
    }
}