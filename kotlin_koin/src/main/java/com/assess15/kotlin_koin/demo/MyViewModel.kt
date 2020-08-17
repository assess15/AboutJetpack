package com.assess15.kotlin_koin.demo

import androidx.lifecycle.ViewModel

class MyViewModel(private val repo: HelloRepository) : ViewModel() {
    fun sayHello() = "${repo.giveHello()} from $this"
}