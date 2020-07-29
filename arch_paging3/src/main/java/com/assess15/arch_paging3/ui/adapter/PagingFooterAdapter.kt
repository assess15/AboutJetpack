package com.assess15.arch_paging3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.assess15.arch_paging3.R

/**
 * Footer
 */

class PagingFooterAdapter(private val adapter: PagingAdapter) :
    LoadStateAdapter<PagingAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: PagingAdapter.ViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingAdapter.ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.footer_layout, parent, false)
        return PagingAdapter.ViewHolder(inflate).also { adapter.refresh() }
    }

}