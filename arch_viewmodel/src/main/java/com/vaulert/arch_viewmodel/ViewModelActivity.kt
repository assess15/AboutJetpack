package com.vaulert.arch_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_viewmodel.*

class ViewModelActivity : AppCompatActivity() {

//    val vm1: VMViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)

        /**
         * 放在onCreate,报错如下:
         * Your activity is not yet attached to the Application instance.
         * You can't request ViewModel before onCreate call.
         */
        val vm2 = ViewModelProvider(this).get(VMViewModel::class.java)

        btnAdd.setOnClickListener {
            tvNumber.text = vm2.number++.toString()
        }
    }


}