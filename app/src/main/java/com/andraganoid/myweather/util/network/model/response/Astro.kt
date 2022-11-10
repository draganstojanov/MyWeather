package com.andraganoid.myweather.util.network.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Astro(

    @Json(name = "moonset")
    val moonset: String? = null,

    @Json(name = "moon_illumination")
    val moonIllumination: String? = null,

    @Json(name = "sunrise")
    val sunrise: String? = null,

    @Json(name = "moon_phase")
    val moonPhase: String? = null,

    @Json(name = "sunset")
    val sunset: String? = null,

    @Json(name = "moonrise")
    val moonrise: String? = null
) : Serializable