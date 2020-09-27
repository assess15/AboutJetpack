package com.vaulert.arch_room.demo2

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "user")
@TypeConverters(CarConverters::class)
data class User(
    @PrimaryKey
    val userId: String,
    val userName: String,
    val userAge: String,
    val cars: List<Car>
)

data class Car(val name: String, val color: String)