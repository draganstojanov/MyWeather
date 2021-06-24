package com.andraganoid.myweather.api

import com.andraganoid.myweather.model.response.AstronomyResponse
import com.andraganoid.myweather.model.response.ForecastResponse

sealed class ResponseState {
    data class Loading(val loaderMsg: String) : ResponseState()
    data class Error(val errorMsg: String) : ResponseState()
    data class AstronomyData(val astronomyResponse: AstronomyResponse?) : ResponseState()
    data class ForecastData(val forecastResponse: ForecastResponse?) : ResponseState()
}