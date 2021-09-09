package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.temperatureFormatter
import com.squareup.moshi.Json
import java.io.Serializable

data class HourItem(

    @Json(name = "will_it_rain")
    val willItRain: Int? = null,

    @Json(name = "feelslike_c")
    val feelslikeC: Double? = null,

    @Json(name = "uv")
    val uv: Double? = null,

    @Json(name = "wind_degree")
    val windDegree: Int? = null,

    @Json(name = "dewpoint_c")
    val dewpointC: Double? = null,

    @Json(name = "windchill_c")
    val windchillC: Double? = null,

    @Json(name = "is_day")
    val isDay: Int? = null,

    @Json(name = "heatindex_c")
    val heatindexC: Double? = null,

    @Json(name = "wind_dir")
    val windDir: String? = null,

    @Json(name = "temp_c")
    val tempC: Double? = null,

    @Json(name = "chance_of_rain")
    val chanceOfRain: String? = null,

    @Json(name = "gust_kph")
    val gustKph: Double? = null,

    @Json(name = "precip_mm")
    val precipMm: Double? = null,

    @Json(name = "cloud")
    val cloud: Int? = null,

    @Json(name = "wind_kph")
    val windKph: Double? = null,

    @Json(name = "condition")
    val condition: Condition? = null,

    @Json(name = "will_it_snow")
    val willItSnow: Int? = null,

    @Json(name = "vis_km")
    val visKm: Double? = null,

    @Json(name = "time_epoch")
    val timeEpoch: Int? = null,

    @Json(name = "humidity")
    val humidity: Int? = null,

    @Json(name = "time")
    val time: String? = null,

    @Json(name = "chance_of_snow")
    val chanceOfSnow: String? = null,

    @Json(name = "pressure_mb")
    val pressureMb: Double? = null
) : Serializable {
    val formattedTemp: String
        get() = temperatureFormatter(tempC!!)

}