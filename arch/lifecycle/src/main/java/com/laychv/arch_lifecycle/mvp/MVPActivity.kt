package com.laychv.arch_lifecycle.mvp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.laychv.arch_lifecycle.databinding.MvpActivityBinding

/**
 * ViewStub
 * 调用一次后移除
 * 是一个大小为0 ，默认不可见的控件
 */
class MVPActivity : AppCompatActivity(), IView {

    lateinit var binding: MvpActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MvpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inflate = binding.stub.inflate()

        binding.showBTN.setOnClickListener {
            inflate.isVisible = true
        }

        binding.hideBTN.setOnClickListener {
            inflate.isVisible = false
        }


        lifecycle.addObserver(IPresenter(this))
    }

    override fun showView() {
        Log.d("b", "mvp show")
    }

    override fun hideView() {
        Log.d("b", "mvp hide")
    }
}