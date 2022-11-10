package com.andraganoid.myweather.util.network.model.response

import com.andraganoid.myweather.util.DateFormatter
import com.squareup.moshi.Json
import java.io.Serializable

data class ForecastDay(

    @Json(name = "date")
    val date: String? = null,

    @Json(name = "astro")
    val astro: Astro? = null,

    @Json(name = "date_epoch")
    val dateEpoch: Int? = null,

    @Json(name = "hour")
    val hour: List<HourItem?>? = null,

    @Json(name = "day")
    val day: Day? = null
) : Serializable {
    val dayName: String
        get() = DateFormatter.dayNameShort(date)

    val dayDate: String
        get() = DateFormatter.dayDate(date)

    val dayMonth: String
        get() = DateFormatter.dayMonth(date)
}