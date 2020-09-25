package com.vaulert.kotlin_koin.demo

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello() = "Hello Koin"
}