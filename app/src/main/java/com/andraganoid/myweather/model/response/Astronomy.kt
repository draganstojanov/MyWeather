package com.andraganoid.myweather.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Astronomy(

    @Json(name = "astro")
    val astro: Astro? = null
) : Serializable