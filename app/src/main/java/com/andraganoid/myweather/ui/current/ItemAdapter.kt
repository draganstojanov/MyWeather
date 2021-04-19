package com.andraganoid.myweather.ui.current

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.databinding.ItemViewBinding

class ItemAdapter(private val itemList: ArrayList<ItemModel>) : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) = holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size

    inner class ItemHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemModel) {
            binding.item = item
            binding.divider.isVisible = layoutPosition < (itemCount - 3)
        }
    }

}