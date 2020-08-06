package com.assess15.arch_paging3.paging3_room.comm

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.assess15.arch_paging3.paging3_room.data.repository.ArticleRepository
import com.assess15.arch_paging3.paging3_room.ui.factory.PagingViewModelFactory

object InjectFactory {

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return PagingViewModelFactory(providePagingRepository(context))
    }

    private fun providePagingRepository(context: Context): ArticleRepository {
//        return ArticleRepository(ArticleDatabaseBuilder.getInstance(context))
        return ArticleRepository()
    }
}