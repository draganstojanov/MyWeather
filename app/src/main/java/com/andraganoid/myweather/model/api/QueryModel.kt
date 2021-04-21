package com.andraganoid.myweather.model.api

data class QueryModel(
    val name: String? = null,
    val region: String? = null,
    val country: String? = null,
    val lon: Double? = null,
    val lat: Double? = null,
)
