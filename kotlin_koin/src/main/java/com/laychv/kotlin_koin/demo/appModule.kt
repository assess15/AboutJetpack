package com.laychv.kotlin_koin.demo

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val single = module {
    // single instance of HelloRepository
    single<HelloRepository> { HelloRepositoryImpl() }
}

val viewModel = module {
    // MyViewModel ViewModel
    viewModel { MyViewModel(get()) }
}

val appModule = listOf(single, viewModel)