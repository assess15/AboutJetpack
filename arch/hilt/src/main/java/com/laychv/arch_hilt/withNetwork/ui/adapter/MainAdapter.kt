package com.laychv.arch_hilt.withNetwork.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laychv.arch_hilt.databinding.ItemLayoutBinding
import com.laychv.arch_hilt.withNetwork.data.model.User

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    lateinit var binding: ItemLayoutBinding

    class DataViewHolder(item: ItemLayoutBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(binding: ItemLayoutBinding, user: User) {
            binding.textViewUserName.text = user.name
            binding.textViewUserEmail.text = user.email
            Glide.with(binding.imageViewAvatar.context)
                .load(user.avatar)
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(binding, users[position])
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}