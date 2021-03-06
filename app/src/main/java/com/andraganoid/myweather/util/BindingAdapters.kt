package com.andraganoid.myweather.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.andraganoid.myweather.R

@BindingAdapter("setImage")
fun setImage(iv: ImageView, url: String?) {
    url?.let {
        iv.load("http:$url") {
            fallback(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher)
        }
    }
}





