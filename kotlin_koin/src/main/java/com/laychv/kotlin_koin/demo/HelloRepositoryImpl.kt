package com.laychv.kotlin_koin.demo

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello() = "Hello Koin"
}