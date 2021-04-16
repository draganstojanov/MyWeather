package com.andraganoid.myweather.util

import com.andraganoid.myweather.model.CurrentWeatherResponse

sealed class ResponseState {
    data class Loading(val LoaderMsg: String) : ResponseState()
    data class Error(val errorMsg: String) : ResponseState()
    data class CurrentWeather(val currentWeather: CurrentWeatherResponse?) : ResponseState()

}