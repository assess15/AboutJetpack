package com.laychv.arch_livedata.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.laychv.arch_livedata.R
import kotlinx.android.synthetic.main.activity_livedata.*

class LiveDataActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)

        viewModel.liveData.observe(this, Observer<Int> {
            tvCount.text = "$it"
        })

        btnAdd.setOnClickListener {
            viewModel.plus(1)
        }
    }
}