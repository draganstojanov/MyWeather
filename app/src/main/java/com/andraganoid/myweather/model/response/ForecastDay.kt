package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.DateFormatter
import com.google.gson.annotations.SerializedName

data class ForecastDay(

    @field:SerializedName("date")
	val date: String? = null,

    @field:SerializedName("astro")
	val astro: Astro? = null,

    @field:SerializedName("date_epoch")
	val dateEpoch: Int? = null,

    @field:SerializedName("hour")
	val hour: List<HourItem?>? = null,

    @field:SerializedName("day")
	val day: Day? = null
) {
    val dayName: String
        get() = DateFormatter.dayNameShort(date)

	val dayDate: String
		get() = DateFormatter.dayDate(date)

	val dayMonth: String
		get() = DateFormatter.dayMonth(date)
}