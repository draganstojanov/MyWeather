package com.andraganoid.myweather.current.ui.model

import com.andraganoid.myweather.util.decimalFormatter

data class ItemModel(val label: String? = null, var value: Any?, val unit: String? = null)

val ItemModel.formattedValue: String
    get() {
        val fValue = if (value is Double) decimalFormatter(value as Double) else value
        return "$fValue ${unit ?: ""}"
    }