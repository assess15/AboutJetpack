package com.assess15.arch_lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLocationObserver()
    }

    private fun initLocationObserver() {
        lifecycle.addObserver(LocationObserver())
    }

}