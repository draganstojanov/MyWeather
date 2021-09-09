package com.andraganoid.myweather.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class AstronomyResponse(

    @Json(name = "location")
    val location: Location? = null,

    @Json(name = "astronomy")
    val astronomy: Astronomy? = null
) : Serializable