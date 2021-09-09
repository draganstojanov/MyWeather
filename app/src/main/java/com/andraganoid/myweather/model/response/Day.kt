package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.decimalFormatter
import com.andraganoid.myweather.util.temperatureFormatter
import com.squareup.moshi.Json
import java.io.Serializable

data class Day(

    @Json(name = "avgvis_km")
    val avgvisKm: Double? = null,

    @Json(name = "uv")
    val uv: Double? = null,

    @Json(name = "avgtemp_c")
    val avgtempC: Double? = null,

    @Json(name = "daily_chance_of_snow")
    val dailyChanceOfSnow: String? = null,

    @Json(name = "maxtemp_c")
    val maxtempC: Double? = null,

    @Json(name = "mintemp_c")
    val mintempC: Double? = null,

    @Json(name = "daily_will_it_rain")
    val dailyWillItRain: Int? = null,

    @Json(name = "avghumidity")
    val avghumidity: Double? = null,

    @Json(name = "condition")
    val condition: Condition? = null,

    @Json(name = "maxwind_kph")
    val maxwindKph: Double? = null,

    @Json(name = "daily_chance_of_rain")
    val dailyChanceOfRain: String? = null,

    @Json(name = "totalprecip_mm")
    val totalprecipMm: Double? = null,

    @Json(name = "daily_will_it_snow")
    val dailyWillItSnow: Int? = null
) : Serializable {
    val formattedTemp: String
        get() = "${temperatureFormatter(mintempC!!)}-${temperatureFormatter(maxtempC!!)}"

    val formattedWind: String
        get() = decimalFormatter(maxwindKph!!)

    val formattedHumidity: String
        get() = decimalFormatter(avghumidity!!)

    val formattedTotalPrec: String
        get() = decimalFormatter(totalprecipMm!!)
}