package com.andraganoid.myweather.util.network.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class AirQuality(

    @Json(name = "no2")
    val no2: Double? = null,

    @Json(name = "o3")
    val o3: Double? = null,

    @Json(name = "us-epa-index")
    val usEpaIndex: Int? = null,

    @Json(name = "so2")
    val so2: Double? = null,

    @Json(name = "pm2_5")
    val pm25: Double? = null,

    @Json(name = "pm10")
    val pm10: Double? = null,

    @Json(name = "co")
    val co: Double? = null,

    @Json(name = "gb-defra-index")
    val gbDefraIndex: Int? = null
) : Serializable