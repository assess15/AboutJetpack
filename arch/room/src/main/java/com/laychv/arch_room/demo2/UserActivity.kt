package com.laychv.arch_room.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.laychv.arch_room.R
import com.laychv.arch_room.databinding.ActivityMainBinding

class UserActivity : AppCompatActivity() {

    var name: String = ""
    private val cars: MutableList<Car> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        cars.add(Car("兰博基尼", "蓝色"))
        cars.add(Car("法拉利", "红色"))
        cars.add(Car("迈凯轮", "黑色"))

        initData()

        binding.btnInsert.setOnClickListener {
            insertUser()
        }

        binding.btnCheck.setOnClickListener {
            initCheck()
        }

        binding.btnClear.setOnClickListener {
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
            binding.tvContent.text = it.toString()
        })
    }

    private fun insertUser() {
        lifecycleScope.launchWhenCreated {
            val i = (1..1000).shuffled().last()
            viewModel.insertUser(User(i, "张三儿$i", "1$i", cars, "男", 10, "tag"))
        }
    }

    private fun initCheck() {
        lifecycleScope.launchWhenCreated {
//            val queryUser = viewModel.queryUser()
//            tvContent.text = queryUser.toString()
        }

        val liveDataUser = viewModel.getLiveDataUser()
        binding.tvContent.text = liveDataUser.toString()
    }

    private fun clearUser() {
        lifecycleScope.launchWhenCreated {
            viewModel.clearUser()
            val queryUser = viewModel.queryUser()
            binding.tvContent.text = queryUser.toString()
        }
    }

}
