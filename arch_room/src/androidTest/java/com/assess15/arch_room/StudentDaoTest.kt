package com.assess15.arch_room

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assess15.arch_room.demo.db.StudentDatabase
import com.assess15.arch_room.demo.db.dao.StudentDao
import com.assess15.arch_room.demo.db.entity.StudentEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class StudentDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wordDao: StudentDao
    private lateinit var db: StudentDatabase

    @Before
    fun createDB() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, StudentDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        wordDao = db.studentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    /**
     * test insert student entity
     */
    @Test
    @Throws(Exception::class)
    fun insert() {
        val word = StudentEntity(100, "zhulei", 25)
        GlobalScope.launch {
            wordDao.insertStudent(word)
        }
        val queryAllStudent = wordDao.queryAllStudent().waitForValue()
        Log.d("fff", queryAllStudent.toString())
    }

    /**
     * test delete student entity
     */
    @Test
    @Throws(Exception::class)
    fun delete() {
        val word = StudentEntity(100, "zhulei", 25)
        GlobalScope.launch {
            wordDao.deleteStudent(word)
        }
        val queryAllStudent = wordDao.queryAllStudent().waitForValue()
        Log.d("fff", queryAllStudent.toString())
    }

}