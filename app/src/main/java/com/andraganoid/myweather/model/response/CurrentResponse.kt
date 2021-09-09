package com.andraganoid.myweather.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class CurrentResponse(

    @Json(name = "current")
    val current: Current? = null,

    @Json(name = "location")
    val location: Location? = null
) : Serializable