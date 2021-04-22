package com.andraganoid.myweather.model.response

import com.google.gson.annotations.SerializedName

data class CurrentResponse(

    @field:SerializedName("current")
	val current: Current? = null,

    @field:SerializedName("location")
	val location: Location? = null
)