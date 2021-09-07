package com.andraganoid.myweather.util

import android.view.View
import android.widget.TextView
import com.andraganoid.myweather.R
import com.andraganoid.myweather.model.db.QueryModel
import com.andraganoid.myweather.model.response.Location
import com.google.android.material.snackbar.Snackbar
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

fun View.actionSnackbar(msg: Any?, action: (View) -> Unit) {
    Snackbar.make(this, msg.toString(), Snackbar.LENGTH_INDEFINITE).setAction("OK") { action(this) }.allowInfiniteLines().show()
}

fun View.longSnackbar(msg: Any?) {
    Snackbar.make(this, msg.toString(), Snackbar.LENGTH_LONG).allowInfiniteLines().show()
}

fun View.showSnackbar(msg: Any?) {
    Snackbar.make(this, msg.toString(), Snackbar.LENGTH_LONG).allowInfiniteLines().show()
}

fun Snackbar.allowInfiniteLines(): Snackbar {
    return apply { (view.findViewById<View?>(R.id.snackbar_text) as? TextView?)?.isSingleLine = false }
}

