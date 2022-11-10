package com.andraganoid.myweather.util.network.model.response

import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.network.model.response.AirQuality
import com.andraganoid.myweather.util.network.model.response.Condition
import com.andraganoid.myweather.util.temperatureFormatter
import com.squareup.moshi.Json
import java.io.Serializable

data class Current(

    @Json(name = "feelslike_c")
    val feelsLikeC: Double? = null,

    @Json(name = "uv")
    val uv: Double? = null,

    @Json(name = "last_updated")
    val lastUpdated: String? = null,

    @Json(name = "wind_degree")
    val windDegree: Int? = null,

    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int? = null,

    @Json(name = "is_day")
    val isDay: Int? = null,

    @Json(name = "air_quality")
    val airQuality: AirQuality? = null,

    @Json(name = "wind_dir")
    val windDir: String? = null,

    @Json(name = "temp_c")
    val tempC: Double? = null,

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

    @Json(name = "vis_km")
    val visKm: Double? = null,

    @Json(name = "humidity")
    val humidity: Int? = null,

    @Json(name = "pressure_mb")
    val pressureMb: Double? = null
) : Serializable {
    val formattedTemp: String
        get() = temperatureFormatter(tempC!!)

    val formattedFeelsLike: String
        get() = temperatureFormatter(feelsLikeC!!)

    val observedAt: String
        get() = DateFormatter.timeFromDate(lastUpdated)

}