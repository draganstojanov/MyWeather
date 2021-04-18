package com.andraganoid.myweather.model

import com.google.gson.annotations.SerializedName

data class AstronomyResponse(

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("astronomy")
	val astronomy: Astronomy? = null
)