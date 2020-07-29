package com.assess15.jetpacks.room.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.assess15.jetpacks.room.db.StudentDatabase
import com.assess15.jetpacks.room.db.dao.StudentDao
import com.assess15.jetpacks.room.db.entity.StudentEntity
import com.assess15.jetpacks.room.db.repository.StudentRepository
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