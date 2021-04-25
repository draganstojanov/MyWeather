package com.andraganoid.myweather.model.response

import com.google.gson.annotations.SerializedName

data class Forecast(

	@field:SerializedName("forecastday")
	val forecastday: List<ForecastDay?>? = null
)