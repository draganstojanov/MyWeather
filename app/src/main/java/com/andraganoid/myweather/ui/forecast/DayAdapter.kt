package com.andraganoid.myweather.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.databinding.DayItemBinding
import com.andraganoid.myweather.model.ForecastDay


class DayAdapter(private val dayList: List<ForecastDay?>?) : RecyclerView.Adapter<DayAdapter.DayHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder = DayHolder(DayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DayHolder, position: Int) = holder.bind(dayList?.get(position))

    override fun getItemCount(): Int = dayList?.size!!

    inner class DayHolder(private val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecastDay: ForecastDay?) {
            binding.forecastDay = forecastDay
            binding.root.setOnClickListener { }
        }
    }

}