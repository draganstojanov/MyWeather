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





