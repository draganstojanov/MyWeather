package com.andraganoid.myweather.model.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Condition(

    @Json(name = "code")
    val code: Int? = null,

    @Json(name = "icon")
    val icon: String? = null,

    @Json(name = "text")
    val text: String? = null
) : Serializable