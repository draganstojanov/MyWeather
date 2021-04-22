package com.andraganoid.myweather.model.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

    @field:SerializedName("current")
	val current: Current? = null,

    @field:SerializedName("location")
	val location: Location? = null,

    @field:SerializedName("forecast")
	val forecast: Forecast? = null
)