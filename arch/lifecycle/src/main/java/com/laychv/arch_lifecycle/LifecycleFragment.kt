package com.laychv.arch_lifecycle

import android.os.Bundle
import androidx.fragment.app.Fragment

class LifecycleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        lifecycle.addObserver(LocationObserver())
    }
}