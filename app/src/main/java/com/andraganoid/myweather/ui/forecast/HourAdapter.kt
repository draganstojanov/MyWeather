package com.andraganoid.myweather.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.HourItemBinding
import com.andraganoid.myweather.model.response.HourItem
import com.andraganoid.myweather.util.DateFormatter


class HourAdapter(private val isToday:Boolean) : RecyclerView.Adapter<HourAdapter.HourHolder>() {

    private var hourList: List<HourItem?>? = arrayListOf()
    private val hourInDay = DateFormatter.hourInDay();

    fun setList(hList: List<HourItem?>) {
        hourList = if (isToday) hList.drop(hourInDay) else hList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourHolder =
        HourHolder(HourItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HourHolder, position: Int) = holder.bind(hourList?.get(position))

    override fun getItemCount(): Int = hourList?.size!!

    inner class HourHolder(private val binding: HourItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hourItem: HourItem?) {
                binding.hour = hourItem
                binding.hourValue = ((if (isToday) hourInDay else 0) +layoutPosition).toString().padStart(2, '0')
                val p = if (Integer.valueOf(hourItem?.chanceOfRain!!) > Integer.valueOf(hourItem.chanceOfSnow!!)) hourItem.chanceOfRain else hourItem.chanceOfSnow
                val prec = "$p ${binding.root.context.getString(R.string.percent)}"
                binding.precipitation.text = prec
                val wind = "${hourItem.windDir} ${hourItem.windKph} ${binding.root.context.getString(R.string.kmh)}"
                binding.wind.text = wind
            }
    }

}
