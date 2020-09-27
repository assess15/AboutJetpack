package com.vaulert.arch_room.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vaulert.arch_room.R
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : AppCompatActivity() {

    var name: String = ""
    private val cars: MutableList<Car> = arrayListOf()
//    private val vm: MainViewModel by viewModels()
//    private val vm by viewModels<MainViewModel>()
//    private val vm by viewModels<MainViewModel>()

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InjectUtils.userViewModelFactory(this)
        ).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cars.add(Car("兰博基尼", "蓝色"))
        cars.add(Car("法拉利", "红色"))
        cars.add(Car("迈凯轮", "黑色"))

        initData()

        btnInsert.setOnClickListener {
            insertUser()
        }

        btnCheck.setOnClickListener {
            initCheck()
        }

        btnClear.setOnClickListener {
            clearUser()
        }
    }

    private fun initData() {
//        lifecycleScope.launchWhenCreated {
//            val queryUser = viewModel.queryUser()
//            tvContent.text = queryUser.toString()
//        }

        /**
         * Notice:
         * Activity -> this
         * Fragment -> viewLifecycleOwner
         */
        viewModel.dataLiveData.observe(this, {
            tvContent.text = it.toString()
        })
    }

    private fun insertUser() {
        lifecycleScope.launchWhenCreated {
            val i = (1..1000).shuffled().last()
            viewModel.insertUser(User("$i", "张三儿$i", "1$i", cars))
        }
    }

    private fun initCheck() {
        lifecycleScope.launchWhenCreated {
            val queryUser = viewModel.queryUser()
            tvContent.text = queryUser.toString()
        }
    }

    private fun clearUser() {
        lifecycleScope.launchWhenCreated {
            viewModel.clearUser()
            val queryUser = viewModel.queryUser()
            tvContent.text = queryUser.toString()
        }
    }

}
