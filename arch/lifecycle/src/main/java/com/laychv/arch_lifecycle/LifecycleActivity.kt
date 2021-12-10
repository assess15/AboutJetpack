package com.laychv.arch_lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.laychv.arch_lifecycle.databinding.ActivityLifecycleBinding

class LifecycleActivity : AppCompatActivity() {

    lateinit var binding: ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLocationObserver()

        initFrag()
    }

    private fun initLocationObserver() {
//        val clazz = Class.forName("androidx.activity.ComponentActivity")
//        val method = clazz.getDeclaredMethod("getLifecycle")
//        val invoke = method.invoke(clazz)
//        invoke.addObserver(LocationObserver(this))
        lifecycle.addObserver(LocationObserver(this))
    }

    private fun initFrag() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<LifecycleFragment>(R.id.container)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(LocationObserver(this))
    }
}