package com.laychv.arch_hilt.simple

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.laychv.arch_hilt.databinding.ActivitySampleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimpleActivity : AppCompatActivity() {

    private val vm: SimpleViewModel by viewModels()
    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv.text = vm.getINJString()

        val tv = vm.tv()
        binding.frame.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT
        binding.frame.layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
        binding.frame.addView(tv)
        tv.text = "看到这句没? 这是注入的TextView显示的"
    }
}