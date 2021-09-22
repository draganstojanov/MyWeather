package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.temperatureFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourItem(

    @SerialName("will_it_rain")
    val willItRain: Int? = null,

    @SerialName("feelslike_c")
    val feelslikeC: Double? = null,

    @SerialName("uv")
    val uv: Double? = null,

    @SerialName("wind_degree")
    val windDegree: Int? = null,

    @SerialName("dewpoint_c")
    val dewpointC: Double? = null,

    @SerialName("windchill_c")
    val windchillC: Double? = null,

    @SerialName("is_day")
    val isDay: Int? = null,

    @SerialName("heatindex_c")
    val heatindexC: Double? = null,

    @SerialName("wind_dir")
    val windDir: String? = null,

    @SerialName("temp_c")
    val tempC: Double? = null,

    @SerialName("chance_of_rain")
    val chanceOfRain: Int? = null,

    @SerialName("gust_kph")
    val gustKph: Double? = null,

    @SerialName("precip_mm")
    val precipMm: Double? = null,

    @SerialName("cloud")
    val cloud: Int? = null,

    @SerialName("wind_kph")
    val windKph: Double? = null,

    @SerialName("condition")
    val condition: Condition? = null,

    @SerialName("will_it_snow")
    val willItSnow: Int? = null,

    @SerialName("vis_km")
    val visKm: Double? = null,

    @SerialName("time_epoch")
    val timeEpoch: Int? = null,

    @SerialName("humidity")
    val humidity: Int? = null,

    @SerialName("time")
    val time: String? = null,

    @SerialName("chance_of_snow")
    val chanceOfSnow: Int? = null,

    @SerialName("pressure_mb")
    val pressureMb: Double? = null
) {
    val formattedTemp: String
        get() = temperatureFormatter(tempC!!)

}