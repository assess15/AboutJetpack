package com.laychv.arch_lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.laychv.arch_lifecycle.databinding.ActivityLifecycleBinding
import com.laychv.arch_lifecycle.mvp.MVPActivity

class LifecycleActivity : AppCompatActivity() {

    lateinit var binding: ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLocationObserver()

        binding.btnMvp.setOnClickListener {
            startActivity(Intent(this, MVPActivity::class.java))
        }
        injectFragment()

        binding.btnDialog.setOnClickListener {
            TipDialog(this).show()
        }
        binding.btnDialog2.setOnClickListener {
            TipDialog2(this).show()
        }
    }

    private fun initLocationObserver() {
//        val clazz = Class.forName("androidx.activity.ComponentActivity")
//        val method = clazz.getDeclaredMethod("getLifecycle")
//        val invoke = method.invoke(clazz)
//        invoke.addObserver(LocationObserver(this))
        lifecycle.addObserver(LocationObserver())
    }

    private fun injectFragment() {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<LifecycleFragment>(R.id.container)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(LocationObserver())
    }
}