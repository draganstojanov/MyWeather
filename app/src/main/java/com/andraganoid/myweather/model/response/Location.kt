package com.andraganoid.myweather.model.response

import com.andraganoid.myweather.util.DateFormatter
import com.squareup.moshi.Json
import java.io.Serializable

data class Location(

    @Json(name = "localtime")
    val localtime: String? = null,

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "localtime_epoch")
    val localtimeEpoch: Int? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "lon")
    val lon: Double? = null,

    @Json(name = "region")
    val region: String? = null,

    @Json(name = "lat")
    val lat: Double? = null,

    @Json(name = "tz_id")
    val tzId: String? = null
) : Serializable {
    val updatedAt: String
        get() = DateFormatter.timeFromDate(localtime)
}