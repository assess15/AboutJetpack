package com.assess15.arch_room.demo.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.assess15.arch_room.demo.db.StudentDatabase
import com.assess15.arch_room.demo.db.dao.StudentDao
import com.assess15.arch_room.demo.db.entity.StudentEntity
import com.assess15.arch_room.demo.db.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(context: Application) : AndroidViewModel(context) {

    private val studentDao: StudentDao
    private var repository: StudentRepository
    val student: LiveData<List<StudentEntity>>

    init {
        val instance = StudentDatabase.getInstance(context)
        studentDao = instance.studentDao()
        repository = StudentRepository(studentDao)
        student = repository.allStudentLD
    }

    fun insert(vararg stu: StudentEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(*stu)
        }
    }

    fun getAllStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllStudent()
        }
    }

    fun delete(vararg stu: StudentEntity) {
        viewModelScope.launch {
            repository.delete()
        }
    }

    fun update(vararg stu: StudentEntity) {
        viewModelScope.launch {
            repository.update()
        }
    }

}