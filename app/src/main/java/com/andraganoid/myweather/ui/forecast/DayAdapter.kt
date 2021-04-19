package com.andraganoid.myweather.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.databinding.DayItemBinding
import com.andraganoid.myweather.model.ForecastDay
import com.andraganoid.myweather.model.HourItem


class DayAdapter(private val dayList: List<ForecastDay?>?) : RecyclerView.Adapter<DayAdapter.DayHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder = DayHolder(DayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DayHolder, position: Int) = holder.bind(dayList?.get(position))

    override fun getItemCount(): Int = dayList?.size!!

    inner class DayHolder(private val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {


        var visibility = false

        fun bind(forecastDay: ForecastDay?) {
            binding.forecastDay = forecastDay
            val hourAdapter = HourAdapter(layoutPosition == 0)
            hourAdapter.setList(arrayListOf())
            binding.hoursRecView.adapter = hourAdapter
            binding.dayContainer.setOnClickListener {
                hourAdapter.setList((if (visibility) arrayListOf() else forecastDay?.hour) as ArrayList<HourItem?>)
                visibility = !visibility
            }
        }
    }

}