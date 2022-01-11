package com.laychv.arch_lifecycle

import android.app.Activity
import android.app.Fragment

/**
 * 演示Lifecycle是如何关联生命周期的
 * 通过添加一个无布局的Fragment
 * Glide的生命周期亦是如此
 */
class HolderFragment : Fragment() {

    private val LIFECYCLE = "lifecycle"

    fun injectFragment(activity: Activity) {
        val manager = activity.fragmentManager
        manager.beginTransaction().add(HolderFragment(), LIFECYCLE)
    }
}