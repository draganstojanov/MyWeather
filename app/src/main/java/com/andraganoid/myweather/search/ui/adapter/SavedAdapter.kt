package com.andraganoid.myweather.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.databinding.SavedItemBinding
import com.andraganoid.myweather.util.database.model.QueryModel
import com.andraganoid.myweather.weather.viewModel.WeatherViewModel

class SavedAdapter(private val viewModel: WeatherViewModel) : ListAdapter<QueryModel, SavedAdapter.SavedHolder>(SavedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedHolder =
        SavedHolder(SavedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SavedHolder, position: Int) = holder.bind(getItem(position))

    inner class SavedHolder(private val binding: SavedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(query: QueryModel) {
            binding.query = query
            binding.root.setOnClickListener { viewModel.getForecast(query.query) }
            binding.deleteBtn.setOnClickListener { viewModel.deleteSavedQuery(query) }
        }
    }

    class SavedDiffCallback : DiffUtil.ItemCallback<QueryModel>() {
        override fun areItemsTheSame(oldItem: QueryModel, newItem: QueryModel): Boolean {
            return oldItem.name == newItem.name;
        }

        override fun areContentsTheSame(oldItem: QueryModel, newItem: QueryModel): Boolean {
            return oldItem == newItem
        }
    }
}
