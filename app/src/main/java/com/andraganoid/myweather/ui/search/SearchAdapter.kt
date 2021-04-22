package com.andraganoid.myweather.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.databinding.SearchItemBinding
import com.andraganoid.myweather.model.response.Location
import com.andraganoid.myweather.ui.WeatherViewModel


class SearchAdapter(private val viewModel: WeatherViewModel) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    var searchList: ArrayList<Location> = arrayListOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, itemCount)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder =
        SearchHolder(SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchHolder, position: Int) = holder.bind(searchList[position])

    override fun getItemCount(): Int = searchList.size

    inner class SearchHolder(private val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(location: Location) {
            binding.location = location
            binding.root.setOnClickListener {
                // TODO viewModel.getWWW(item)
            }
        }
    }

}