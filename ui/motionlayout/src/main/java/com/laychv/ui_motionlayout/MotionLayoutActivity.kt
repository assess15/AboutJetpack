package com.laychv.ui_motionlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laychv.ui_motionlayout.databinding.ActivityMotionlayoutBinding

class MotionLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMotionlayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}