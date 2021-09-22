package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.DateFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(

    @SerialName("date")
    val date: String? = null,

    @SerialName("astro")
    val astro: Astro? = null,

    @SerialName("date_epoch")
    val dateEpoch: Int? = null,

    @SerialName("hour")
    val hour: List<HourItem?>? = null,

    @SerialName("day")
    val day: Day? = null
) {
    val dayName: String
        get() = DateFormatter.dayNameShort(date)

    val dayDate: String
        get() = DateFormatter.dayDate(date)

    val dayMonth: String
        get() = DateFormatter.dayMonth(date)
}