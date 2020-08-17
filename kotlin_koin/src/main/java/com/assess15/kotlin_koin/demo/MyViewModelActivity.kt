package com.assess15.kotlin_koin.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.assess15.kotlin_koin.R
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyViewModelActivity : AppCompatActivity() {

    // Lazy Inject ViewModel
    val vm: MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin)

        // 必须放在生命周期里面
        val model: MyViewModel = getViewModel()
        Log.d("aa", model.sayHello())

        Log.d("aa", vm.sayHello())
    }
}
