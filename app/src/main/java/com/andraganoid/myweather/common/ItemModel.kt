package com.andraganoid.myweather.common

import java.math.RoundingMode
import java.text.DecimalFormat

class ItemModel(val label: String? = null, var value: Any?, val unit: String? = null) {

    val formattedValue: String
        get() {
            val fValue = if (value is Double) roundOffDecimal(value as Double) else value
            return "$fValue ${unit ?: ""}"
        }

    private fun roundOffDecimal(number: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_UP
        val shortDouble = df.format(number).toDouble()
        val longValue = shortDouble.toLong()
        return (if (shortDouble == longValue.toDouble()) longValue else shortDouble).toString()
    }

}