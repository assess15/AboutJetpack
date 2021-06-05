package com.laychv.arch_paging3.paging3.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.laychv.arch_paging3.paging3.data.model.DataList
import com.laychv.arch_paging3.paging3.ui.adapter.viewholder.PagingViewHolder

class PagingAdapter : PagingDataAdapter<DataList, PagingViewHolder>(diff) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        val diff = object : DiffUtil.ItemCallback<DataList>() {

            override fun areItemsTheSame(oldItem: DataList, newItem: DataList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DataList, newItem: DataList): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            when (holder) {
                is PagingViewHolder -> {
                    PagingViewHolder(holder.itemView).bind(item)
                }
                else -> Unit
            }
        }
    }
}