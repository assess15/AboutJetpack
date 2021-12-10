package com.laychv.arch_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.laychv.arch_viewmodel.databinding.ActivityViewmodelBinding

class ViewModelActivity : AppCompatActivity() {

    //    val vm1: VMViewModel by viewModels()
    private lateinit var binding: ActivityViewmodelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewmodelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 放在onCreate,报错如下:
         * Your activity is not yet attached to the Application instance.
         * You can't request ViewModel before onCreate call.
         */
        val vm2 = ViewModelProvider(this).get(VMViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            binding.tvNumber.text = vm2.number++.toString()
        }
    }
}