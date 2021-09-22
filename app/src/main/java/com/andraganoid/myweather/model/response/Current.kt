package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.temperatureFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(

    @SerialName("feelslike_c")
    val feelsLikeC: Double? = null,

    @SerialName("uv")
    val uv: Double? = null,

    @SerialName("last_updated")
    val lastUpdated: String? = null,

    @SerialName("wind_degree")
    val windDegree: Int? = null,

    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Int? = null,

    @SerialName("is_day")
    val isDay: Int? = null,

    @SerialName("air_quality")
    val airQuality: AirQuality? = null,

    @SerialName("wind_dir")
    val windDir: String? = null,

    @SerialName("temp_c")
    val tempC: Double? = null,

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

    @SerialName("vis_km")
    val visKm: Double? = null,

    @SerialName("humidity")
    val humidity: Int? = null,

    @SerialName("pressure_mb")
    val pressureMb: Double? = null
) {
    val formattedTemp: String
        get() = temperatureFormatter(tempC!!)

    val formattedFeelsLike: String
        get() = temperatureFormatter(feelsLikeC!!)

    val observedAt: String
        get() = DateFormatter.timeFromDate(lastUpdated)

}