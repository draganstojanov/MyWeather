package com.andraganoid.myweather.util

import java.math.RoundingMode
import java.text.DecimalFormat

 fun decimalFormatter(number: Number): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_UP
    val shortDouble = df.format(number).toDouble()
    val longValue = shortDouble.toLong()
    return (if (shortDouble == longValue.toDouble()) longValue else shortDouble).toString()
}

fun temperatureFormatter(temp:Number):String{
    return "${decimalFormatter(temp)}\u00B0"
}