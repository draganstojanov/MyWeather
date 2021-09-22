package com.andraganoid.myweather.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(

    @SerialName("current")
    val current: Current? = null,

    @SerialName("location")
    val location: Location? = null,

    @SerialName("forecast")
    val forecast: Forecast? = null
)