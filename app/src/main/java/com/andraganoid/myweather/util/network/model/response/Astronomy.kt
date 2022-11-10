package com.andraganoid.myweather.util.network.model.response

import com.andraganoid.myweather.util.network.model.response.Astro
import com.squareup.moshi.Json
import java.io.Serializable

data class Astronomy(

    @Json(name = "astro")
    val astro: Astro? = null
) : Serializable