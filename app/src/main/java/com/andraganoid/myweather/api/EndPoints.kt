package com.andraganoid.myweather.api

import com.andraganoid.myweather.util.Key

class EndPoints {
    companion object {
        const val KEY = Key.KEY
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val CURRENT = "current.json"
        const val FORECAST = "forecast.json"
        const val HISTORY = "history.json"
        const val ASTRONOMY = "astronomy.json"
    }
}