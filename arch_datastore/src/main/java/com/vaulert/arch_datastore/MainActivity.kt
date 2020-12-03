package com.vaulert.arch_datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.vaulert.arch_datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        test()

        test1()
    }

    private fun test1() {
        val userPreferences = UserPreferences(this)

        binding.btn.setOnClickListener {
            lifecycleScope.launch {
                userPreferences.saveBookmark("测试")
            }
        }

        userPreferences.bookmark.asLiveData().observe(this, {
            binding.tv.text = it
        })
    }

    private fun test() {
        // create a prefer datastore
        viewModel.create(this)

        // write a prefer datastore
        binding.btn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.write()
            }
        }

        // read a prefer datastore
        val read = viewModel.read()
        lifecycleScope.launch {
            read.collectLatest {
                binding.tv.text = it.toString()
            }
        }
    }


}