package com.laychv.arch_room.demo.db.repository

import androidx.lifecycle.LiveData
import com.laychv.arch_room.demo.db.dao.StudentDao
import com.laychv.arch_room.demo.db.entity.StudentEntity

/**
 * 数据操作
 */

class StudentRepository(private val studentDao: StudentDao) {

    var allStudentLD: LiveData<List<StudentEntity>> = studentDao.queryAllStudent()

    fun getAllStudent(): LiveData<List<StudentEntity>> {
        return allStudentLD
    }

    /**
     * insert
     */
    suspend fun insert(vararg stu: StudentEntity) {
        studentDao.insertStudent(*stu)
    }

    /**
     * delete
     */
    suspend fun delete() {
        studentDao.deleteAllStudent()
    }

    /**
     * update
     */
    suspend fun update() {
        studentDao.updateStudent()
    }

    /**
     * query
     */
    suspend fun queryAllStudent() {
        studentDao.queryAllStudent()
    }
}