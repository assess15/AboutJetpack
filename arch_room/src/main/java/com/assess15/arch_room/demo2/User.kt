package com.assess15.arch_room.demo2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userId: String,
    val userName: String,
    val userAge: String
)