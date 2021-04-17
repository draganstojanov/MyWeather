package com.andraganoid.myweather.api

import com.andraganoid.myweather.util.EndPoint
import com.andraganoid.myweather.util.ResponseState
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCurrentWeather(): ResponseState {
        val r = apiService.getCurrentWeather(key = EndPoint.KEY, query = "Belgrade")//todo
        return if (r.isSuccessful) ResponseState.CurrentWeather(r.body()) else ResponseState.Error(r.message())
    }


}