package com.andraganoid.myweather.model

import com.google.gson.annotations.SerializedName

data class AirQuality(

	@field:SerializedName("no2")
	val no2: Double? = null,

	@field:SerializedName("o3")
	val o3: Double? = null,

	@field:SerializedName("us-epa-index")
	val usEpaIndex: Int? = null,

	@field:SerializedName("so2")
	val so2: Double? = null,

	@field:SerializedName("pm2_5")
	val pm25: Double? = null,

	@field:SerializedName("pm10")
	val pm10: Double? = null,

	@field:SerializedName("co")
	val co: Double? = null,

	@field:SerializedName("gb-defra-index")
	val gbDefraIndex: Int? = null
)