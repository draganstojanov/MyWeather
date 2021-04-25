package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.DateFormatter
import com.google.gson.annotations.SerializedName

data class Location(

	@field:SerializedName("localtime")
	val localtime: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("localtime_epoch")
	val localtimeEpoch: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("tz_id")
	val tzId: String? = null
) {
    val updatedAt: String
        get() = DateFormatter.timeFromDate(localtime)
}