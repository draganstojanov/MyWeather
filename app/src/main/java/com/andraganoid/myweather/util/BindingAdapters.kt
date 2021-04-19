package com.andraganoid.myweather.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.andraganoid.myweather.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@BindingAdapter("setImage")
fun setImage(iv: ImageView, url: String?) {
    url?.let {
        iv.load("http:$url") {
            fallback(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher)
        }
    }
}

@BindingAdapter("weekDayShort")
fun dayNameShort(tv: TextView, dateString: String?) {
    if (dateString != null) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        val date: LocalDateTime = LocalDateTime.parse(dateString, formatter)
        tv.text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }
}







