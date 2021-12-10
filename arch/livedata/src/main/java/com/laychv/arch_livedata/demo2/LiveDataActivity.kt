package com.laychv.arch_livedata.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.laychv.arch_livedata.databinding.ActivityLivedataBinding

class LiveDataActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel
    private lateinit var binding: ActivityLivedataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLivedataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)

        viewModel.liveData.observe(this, Observer<Int> {
            binding.tvCount.text = "$it"
        })

        binding.btnAdd.setOnClickListener {
            viewModel.plus(1)
        }
    }
}