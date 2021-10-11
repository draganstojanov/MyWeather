package com.andraganoid.myweather.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.DayItemBinding
import com.andraganoid.myweather.model.response.ForecastDay
import com.andraganoid.myweather.model.response.HourItem


class DayAdapter(private val dayList: List<ForecastDay?>?) : RecyclerView.Adapter<DayAdapter.DayHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder =
        DayHolder(DayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DayHolder, position: Int) = holder.bind(dayList?.get(position))

    override fun getItemCount(): Int = dayList?.size!!

    inner class DayHolder(private val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var visibility = false

        fun bind(forecastDay: ForecastDay?) {
            binding.forecastDay = forecastDay

            val p =
                if (Integer.valueOf(forecastDay?.day?.dailyChanceOfRain!!) > Integer.valueOf(forecastDay.day.dailyChanceOfSnow!!)) forecastDay.day.dailyChanceOfRain else forecastDay.day.dailyChanceOfSnow
            val prChance = "${binding.root.context.getString(R.string.pr_chance)} $p ${binding.root.context.getString(R.string.percent)}"
            binding.prChance.text = prChance

            val hourAdapter = HourAdapter(layoutPosition == 0)
            hourAdapter.setList(arrayListOf())
            binding.hoursRecView.adapter = hourAdapter
            binding.dayContainer.setOnClickListener {
                hourAdapter.setList((if (visibility) arrayListOf() else forecastDay.hour) as ArrayList<HourItem?>)
                visibility = !visibility
            }
        }
    }

}