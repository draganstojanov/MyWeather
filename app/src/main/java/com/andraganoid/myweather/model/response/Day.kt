package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.decimalFormatter
import com.andraganoid.myweather.util.temperatureFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(

    @SerialName("avgvis_km")
    val avgvisKm: Double? = null,

    @SerialName("uv")
    val uv: Double? = null,

    @SerialName("avgtemp_c")
    val avgtempC: Double? = null,

    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int? = null,

    @SerialName("maxtemp_c")
    val maxtempC: Double? = null,

    @SerialName("mintemp_c")
    val mintempC: Double? = null,

    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int? = null,

    @SerialName("avghumidity")
    val avghumidity: Double? = null,

    @SerialName("condition")
    val condition: Condition? = null,

    @SerialName("maxwind_kph")
    val maxwindKph: Double? = null,

    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int? = null,

    @SerialName("totalprecip_mm")
    val totalprecipMm: Double? = null,

    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int? = null
) {
    val formattedTemp: String
        get() = "${temperatureFormatter(mintempC!!)}-${temperatureFormatter(maxtempC!!)}"

    val formattedWind: String
        get() = decimalFormatter(maxwindKph!!)

    val formattedHumidity: String
        get() = decimalFormatter(avghumidity!!)

    val formattedTotalPrec: String
        get() = decimalFormatter(totalprecipMm!!)
}