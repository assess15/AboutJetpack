package com.assess15.jetpacks.room.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assess15.jetpacks.R
import com.assess15.jetpacks.room.db.entity.StudentEntity
import com.assess15.jetpacks.room.ui.adapter.StudentListAdapter
import com.assess15.jetpacks.room.ui.vm.RoomViewModel
import kotlinx.android.synthetic.main.jetpack_activity_room.*

class RoomActivity : AppCompatActivity() {

    lateinit var vm: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jetpack_activity_room)

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

        btn1.setOnClickListener {
            vm.insert(stu, stu1, stu2, stu3)
        }

        btn2.setOnClickListener {
            vm.delete(stu)
        }

        btn3.setOnClickListener {

        }

        btn4.setOnClickListener {

        }
    }

}