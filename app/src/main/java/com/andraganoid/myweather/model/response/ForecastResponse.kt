package com.andraganoid.myweather.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class ForecastResponse(

    @Json(name = "current")
    val current: Current? = null,

    @Json(name = "location")
    val location: Location? = null,

    @Json(name = "forecast")
    val forecast: Forecast? = null
) : Serializable