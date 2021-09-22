package com.andraganoid.myweather.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Astronomy(

    @SerialName("astro")
    val astro: Astro? = null
)