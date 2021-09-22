package com.andraganoid.myweather.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstronomyResponse(

    @SerialName("location")
    val location: Location? = null,

    @SerialName("astronomy")
    val astronomy: Astronomy? = null
)