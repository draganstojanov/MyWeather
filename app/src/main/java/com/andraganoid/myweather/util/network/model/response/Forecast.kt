package com.andraganoid.myweather.util.network.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Forecast(

    @Json(name = "forecastday")
    val forecastday: List<ForecastDay?>? = null
) : Serializable