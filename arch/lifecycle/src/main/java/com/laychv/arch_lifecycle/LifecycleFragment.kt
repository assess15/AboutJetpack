package com.laychv.arch_lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.laychv.arch_lifecycle.databinding.FragmentLifecycleBinding

class LifecycleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflate = FragmentLifecycleBinding.inflate(inflater, container, false)
        return inflate.root
    }

    private fun init() {
        lifecycle.addObserver(LocationObserver(requireContext()))
    }
}