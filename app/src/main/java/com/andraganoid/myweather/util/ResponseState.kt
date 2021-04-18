package com.andraganoid.myweather.util

import com.andraganoid.myweather.model.AstronomyResponse
import com.andraganoid.myweather.model.CurrentResponse

sealed class ResponseState {
    data class Loading(val loaderMsg: String) : ResponseState()
    data class Error(val errorMsg: String) : ResponseState()
    data class CurrentWeather(val currentResponse: CurrentResponse?) : ResponseState()
    data class AstroData(val astronomyResponse: AstronomyResponse?) : ResponseState()
}