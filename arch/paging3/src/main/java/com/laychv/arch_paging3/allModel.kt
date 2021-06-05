package com.laychv.arch_paging3

import com.laychv.arch_paging3.paging3_package.base.BaseViewModel
import com.laychv.arch_paging3.paging3_package.data.repository.ArticleRepository
import com.laychv.arch_paging3.paging3_package.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel(get()) }
}

val repo = module {
    factory { ArticleRepository(get()) }
}

val appModel = listOf(viewModel, repo)