package com.laychv.arch_paging3.paging3_package.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.laychv.arch_paging3.R
import com.laychv.arch_paging3.databinding.FooterLayoutBinding

/**
 * Footer
 */

class MainFooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<NetworkStateViewHolder>() {

    override fun onBindViewHolder(holder: NetworkStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateViewHolder = NetworkStateViewHolder(parent, retry)
}

class NetworkStateViewHolder(parent: ViewGroup, val retry: () -> Unit) : RecyclerView.ViewHolder(
    FooterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    init {
        itemView.findViewById<LinearLayout>(R.id.mRetry).setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        itemView.findViewById<ProgressBar>(R.id.mProgressBar).isVisible =
            loadState is LoadState.Loading
        itemView.findViewById<LinearLayout>(R.id.mRetry).isVisible = loadState is LoadState.Error
    }
}