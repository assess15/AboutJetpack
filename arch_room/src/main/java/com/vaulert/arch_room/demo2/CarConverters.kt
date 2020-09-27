package com.vaulert.arch_room.demo2

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarConverters {

    @TypeConverter
    fun stringToObject(value: String): List<Car> {
        val type = object : TypeToken<List<Car>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun objectToString(list: List<Car>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}