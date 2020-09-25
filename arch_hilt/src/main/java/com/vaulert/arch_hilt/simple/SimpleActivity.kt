package com.vaulert.arch_hilt.simple

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vaulert.arch_hilt.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sample.*

@AndroidEntryPoint
class SimpleActivity : AppCompatActivity() {

    private val vm: SimpleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        tv.text = vm.getINJString()

        val tv = vm.tv()
        frame.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT
        frame.layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
        frame.addView(tv)
        tv.text = "看到这句没? 这是注入的TextView显示的"
    }

}