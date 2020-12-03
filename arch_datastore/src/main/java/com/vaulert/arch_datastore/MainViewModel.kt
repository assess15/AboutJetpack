package com.vaulert.arch_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.ViewModel
import com.vaulert.lib_base.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {

    // Key的类型仅限: Int,Long,Boolean,Float,String
    private val counter = preferencesKey<Int>("counter")

    lateinit var ds: DataStore<Preferences>

    fun create(context: Context) {
        ds = context.createDataStore(name = "测试DataStore存储")
    }

    suspend fun write() {
        ds.edit { setting ->
//            val value = setting[counter] ?: 0
//            setting[counter] = value + 2

            var i = setting[counter]
            Logger.d("${i}")
        }
    }

    fun read(): Flow<Int> {
        val counterFlow: Flow<Int> = ds.data.map { pre ->
            pre[counter] ?: 0
        }
        return counterFlow
    }

}