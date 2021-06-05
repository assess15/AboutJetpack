package com.laychv.arch_paging3.paging3_package.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.laychv.arch_paging3.R
import kotlinx.android.synthetic.main.layout_footer.view.*

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
    LayoutInflater.from(parent.context).inflate(R.layout.layout_footer, parent, false)
) {

    init {
        itemView.mRetry.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        itemView.mProgressBar.isVisible = loadState is LoadState.Loading
        itemView.mRetry.isVisible = loadState is LoadState.Error
    }
}