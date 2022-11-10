package com.andraganoid.myweather.util.network.constant

import com.andraganoid.myweather.util.API_KEY

class EndPoints {
    companion object {
        const val KEY = API_KEY
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val CURRENT = "current.json"
        const val FORECAST = "forecast.json"
        const val HISTORY = "history.json"
        const val ASTRONOMY = "astronomy.json"
    }
}