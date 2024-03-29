package com.andraganoid.myweather.current.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.current.ui.model.ItemModel
import com.andraganoid.myweather.current.ui.model.formattedValue
import com.andraganoid.myweather.databinding.ItemViewBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    var itemList: ArrayList<ItemModel> = arrayListOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, itemCount)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size

    inner class ItemHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemModel) {
            binding.itemLabel.text = item.label
            binding.itemValue.text = item.formattedValue
            binding.divider.isVisible = layoutPosition < (itemCount - 3)
        }
    }

}