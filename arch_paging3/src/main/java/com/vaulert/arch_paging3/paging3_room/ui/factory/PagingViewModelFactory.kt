package com.vaulert.arch_paging3.paging3_room.ui.factory

import com.vaulert.arch_paging3.paging3_room.base.BaseViewModel
import com.vaulert.arch_paging3.paging3_room.base.BaseViewModelFactory
import com.vaulert.arch_paging3.paging3_room.data.repository.ArticleRepository
import com.vaulert.arch_paging3.paging3_room.ui.viewmodel.PagingViewModel

class PagingViewModelFactory(private val repository: ArticleRepository) :
    BaseViewModelFactory(repository) {

    override fun onCreate(): BaseViewModel {
        return PagingViewModel(repository)
    }

}