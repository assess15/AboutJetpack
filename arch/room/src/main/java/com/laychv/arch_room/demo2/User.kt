package com.laychv.arch_room.demo2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "User")
@TypeConverters(CarConverters::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    val userId: Int,
    @ColumnInfo(name = "UserName")
    val userName: String,
    @ColumnInfo(name = "UserAge")
    val userAge: String,
    @ColumnInfo(name = "UserCars")
    val cars: List<Car>,
    @ColumnInfo(name = "UserSex")
    val sex: String,
    @ColumnInfo(name = "UserNation")
    val nation: Int,
    @ColumnInfo(name = "UserFlag")
    val flag: String
)

data class Car(val name: String, val color: String)