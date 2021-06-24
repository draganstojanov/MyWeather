package com.andraganoid.myweather.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.databinding.SavedItemBinding
import com.andraganoid.myweather.model.db.QueryModel
import com.andraganoid.myweather.ui.WeatherViewModel


class SavedAdapter(private val viewModel: WeatherViewModel) : RecyclerView.Adapter<SavedAdapter.SavedHolder>() {

    var savedList: List<QueryModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedHolder =
        SavedHolder(SavedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SavedHolder, position: Int) = holder.bind(savedList[position])

    override fun getItemCount(): Int = savedList.size

    inner class SavedHolder(private val binding: SavedItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(query: QueryModel) {
            binding.query = query
            binding.root.setOnClickListener { viewModel.getForecast(query.query) }
            binding.deleteBtn.setOnClickListener { viewModel.deleteSavedQuery(query) }
        }
    }
}