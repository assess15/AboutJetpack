package com.laychv.ui_motionlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.laychv.ui_motionlayout.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
        }
    }
}