package com.laychv.arch_paging3.paging3_package.view.fragment

import androidx.fragment.app.viewModels
import androidx.paging.PagingDataAdapter
import com.laychv.arch_paging3.paging3_package.base.BasePagingVMFragment
import com.laychv.arch_paging3.paging3_package.base.BaseViewModel
import com.laychv.arch_paging3.paging3_package.data.model.DataList
import com.laychv.arch_paging3.paging3_package.view.adapter.MainAdapter
import com.laychv.arch_paging3.paging3_package.viewmodel.MainViewModel

class MainFragment : BasePagingVMFragment<DataList, MainViewModel, MainAdapter.ViewHolder>() {

    val vm: MainViewModel by viewModels()

    override fun getAdapter(): PagingDataAdapter<DataList, MainAdapter.ViewHolder> {
        return MainAdapter()
    }

    override fun getViewModel(): BaseViewModel = vm
}