package com.andraganoid.myweather.model

import com.google.gson.annotations.SerializedName

data class Forecast(

	@field:SerializedName("forecastday")
	val forecastday: List<ForecastDay?>? = null
)