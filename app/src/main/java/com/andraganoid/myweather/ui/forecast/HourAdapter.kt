package com.andraganoid.myweather.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.HourItemBinding
import com.andraganoid.myweather.model.HourItem


class HourAdapter(private val hourList: List<HourItem?>?) : RecyclerView.Adapter<HourAdapter.HourHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourHolder =
        HourHolder(HourItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HourHolder, position: Int) = holder.bind(hourList?.get(position))

    override fun getItemCount(): Int = hourList?.size!!

    inner class HourHolder(private val binding: HourItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hourItem: HourItem?) {
            binding.hour = hourItem
            binding.hourValue = layoutPosition.toString().padStart(2, '0')
            val p = if (Integer.valueOf(hourItem?.chanceOfRain) > Integer.valueOf(hourItem?.chanceOfSnow)) hourItem?.chanceOfRain else hourItem?.chanceOfSnow
            val prec= "$p ${binding.root.context.getString(R.string.percent)}"
            binding.precipitation.text = prec
            val wind = "${hourItem?.windDir} ${hourItem?.windKph} ${binding.root.context.getString(R.string.kmh)}"
            binding.wind.text = wind
        }
    }

}
