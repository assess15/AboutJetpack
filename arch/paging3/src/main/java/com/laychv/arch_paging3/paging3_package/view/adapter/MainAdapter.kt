package com.laychv.arch_paging3.paging3_package.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.laychv.arch_paging3.databinding.ItemPaging3Binding
import com.laychv.arch_paging3.paging3_package.data.model.DataList

class MainAdapter : PagingDataAdapter<DataList, MainAdapter.ViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<DataList>() {

            override fun areContentsTheSame(oldItem: DataList, newItem: DataList): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: DataList, newItem: DataList): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
//        holder.txt.text = item?.title
        item?.let { holder.bindData(it) }
    }

    inner class ViewHolder(val view: View, val binding: ItemPaging3Binding) :
        RecyclerView.ViewHolder(view) {
        //        val txt: TextView = view.findViewById<TextView>(R.id.tvName)
        fun bindData(dataList: DataList) {
            binding.item = dataList
            binding.tvName.setOnClickListener {
                Toast.makeText(
                    view.context,
                    "\uD83D\uDE28 Wops ${dataList.title}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
//        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
//        return ViewHolder(inflate)
        val from = LayoutInflater.from(parent.context)
        val inflate = ItemPaging3Binding.inflate(from, parent, false)
        return ViewHolder(inflate.root, inflate)
    }
}