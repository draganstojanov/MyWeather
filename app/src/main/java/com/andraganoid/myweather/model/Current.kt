package com.andraganoid.myweather.model

import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.temperatureFormatter
import com.google.gson.annotations.SerializedName

data class Current(

    @field:SerializedName("feelslike_c")
    val feelsLikeC: Double? = null,

    @field:SerializedName("uv")
    val uv: Double? = null,

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("wind_degree")
    val windDegree: Int? = null,

    @field:SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int? = null,

    @field:SerializedName("is_day")
    val isDay: Int? = null,

    @field:SerializedName("air_quality")
    val airQuality: AirQuality? = null,

    @field:SerializedName("wind_dir")
    val windDir: String? = null,

    @field:SerializedName("temp_c")
    val tempC: Double? = null,

    @field:SerializedName("gust_kph")
    val gustKph: Double? = null,

    @field:SerializedName("precip_mm")
    val precipMm: Double? = null,

    @field:SerializedName("cloud")
    val cloud: Int? = null,

    @field:SerializedName("wind_kph")
    val windKph: Double? = null,

    @field:SerializedName("condition")
    val condition: Condition? = null,

    @field:SerializedName("vis_km")
    val visKm: Double? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("pressure_mb")
    val pressureMb: Double? = null
) {
    val formattedTemp: String
        get() = temperatureFormatter(tempC!!)

    val formattedFeelsLike: String
        get() = temperatureFormatter(feelsLikeC!!)

    val observedAt: String
        get() = DateFormatter.timeFromDate(lastUpdated)

}