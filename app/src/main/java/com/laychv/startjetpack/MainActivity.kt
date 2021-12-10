package com.laychv.startjetpack

import android.app.Activity
import android.os.Bundle
import com.laychv.startjetpack.databinding.ActivityMainBinding

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(layoutInflater)
        setContentView(inflate.root)
    }
}