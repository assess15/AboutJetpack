package com.laychv.arch_paging3.paging3_room.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.laychv.arch_paging3.paging3_room.data.model.ArticleList
import com.laychv.arch_paging3.paging3_room.ui.adapter.viewholder.PagingViewHolder

class PagingAdapter : PagingDataAdapter<ArticleList, PagingViewHolder>(
    diff
) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        val diff = object : DiffUtil.ItemCallback<ArticleList>() {

            override fun areItemsTheSame(oldItem: ArticleList, newItem: ArticleList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleList, newItem: ArticleList): Boolean =
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