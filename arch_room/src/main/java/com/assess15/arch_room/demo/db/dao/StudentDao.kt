package com.assess15.jetpacks.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assess15.jetpacks.room.db.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(vararg student: StudentEntity)

    @Delete
    suspend fun deleteStudent(vararg student: StudentEntity)

    @Update
    suspend fun updateStudent(vararg student: StudentEntity)

    @Query("DELETE FROM student")
    suspend fun deleteAllStudent()

    @Query("SELECT * FROM student ORDER BY id DESC")
    fun queryAllStudent(): LiveData<List<StudentEntity>>

    /**
     * 错误写法,导致编译不通过,如下:
     */
//    fun queryAllStudent(): MutableLiveData<List<StudentEntity>>
//    suspend fun queryAllStudent(): MutableLiveData<List<StudentEntity>>
//    suspend fun queryAllStudent(): LiveData<List<StudentEntity>>

}