package com.assess15.kotlin_koin.network

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val single = module {
    // single instance of HelloRepository
    single<HomeApi> { HomeService }
}

val viewModel = module {
    // MyViewModel ViewModel
    viewModel { HomeViewModel(get()) }
}

val factory = module {
    factory { HomeRepository(get()) }
}

val networkModule = listOf(single, viewModel, factory)