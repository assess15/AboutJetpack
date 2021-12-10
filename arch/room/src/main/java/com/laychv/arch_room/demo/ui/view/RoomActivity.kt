package com.laychv.arch_room.demo.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laychv.arch_room.R
import com.laychv.arch_room.databinding.ActivityRoomBinding
import com.laychv.arch_room.demo.db.entity.StudentEntity
import com.laychv.arch_room.demo.ui.adapter.StudentListAdapter
import com.laychv.arch_room.demo.ui.vm.RoomViewModel

class RoomActivity : AppCompatActivity() {

    private lateinit var vm: RoomViewModel
    private lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_room)

        vm = ViewModelProvider(this).get(RoomViewModel::class.java)

        initView()
    }

    private fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = StudentListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val stu = StudentEntity(0, "zhulei", 25)
        val stu1 = StudentEntity(1, "zhulei2", 25)
        val stu2 = StudentEntity(2, "zhulei3", 25)
        val stu3 = StudentEntity(3, "zhulei4", 25)

        vm.student.observe(this, Observer {
            adapter.setWords(it)
        })

        binding.btn1.setOnClickListener {
            vm.insert(stu, stu1, stu2, stu3)
        }

        binding.btn2.setOnClickListener {
            vm.delete(stu)
        }

        binding.btn3.setOnClickListener {

        }

        binding.btn4.setOnClickListener {

        }
    }

}