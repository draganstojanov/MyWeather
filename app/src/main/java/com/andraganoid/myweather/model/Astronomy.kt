package com.andraganoid.myweather.model

import com.google.gson.annotations.SerializedName

data class Astronomy(

	@field:SerializedName("astro")
	val astro: Astro? = null
)