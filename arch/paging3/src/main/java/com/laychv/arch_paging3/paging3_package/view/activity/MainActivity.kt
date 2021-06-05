package com.laychv.arch_paging3.paging3_package.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.laychv.arch_paging3.R
import com.laychv.arch_paging3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.container).navigateUp()
}