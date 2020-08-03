package com.assess15.arch_paging3.paging3_room.comm

import androidx.lifecycle.ViewModelProvider
import com.assess15.arch_paging3.paging3_room.data.repository.ArticleRepository
import com.assess15.arch_paging3.paging3_room.ui.factory.PagingViewModelFactory

object InjectFactory {

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return PagingViewModelFactory(providePagingRepository())
    }

    private fun providePagingRepository(): ArticleRepository {
        return ArticleRepository()
    }
}