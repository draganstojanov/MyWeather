package com.andraganoid.myweather.util

import com.andraganoid.myweather.model.db.QueryModel
import com.andraganoid.myweather.model.response.Location
import java.math.RoundingMode
import java.text.DecimalFormat

fun decimalFormatter(number: Number): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_UP
    val shortDouble = df.format(number).toDouble()
    val longValue = shortDouble.toLong()
    return (if (shortDouble == longValue.toDouble()) longValue else shortDouble).toString()
}

fun temperatureFormatter(temp: Number): String = "${decimalFormatter(temp)}\u00B0"

fun Location.toQueryModel() = QueryModel(
    name = name,
    country = country,
    region = region,
    lat = lat,
    lon = lon
)