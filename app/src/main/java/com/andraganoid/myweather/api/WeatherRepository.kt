package com.andraganoid.myweather.api

import com.andraganoid.myweather.util.EndPoint
import com.andraganoid.myweather.util.ResponseState
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCurrentWeather(): ResponseState {
        val r = apiService.getCurrentWeather(key = EndPoint.KEY, query = "Belgrade",aqi = "yes")//todo
        return if (r.isSuccessful) ResponseState.CurrentWeather(r.body()) else ResponseState.Error(r.message())
    }

    suspend fun getAstronomy(): ResponseState {
        val r = apiService.getAstronomy(key = EndPoint.KEY, query = "Belgrade",date="2021-04-18")//todo
        return if (r.isSuccessful) ResponseState.AstroData(r.body()) else ResponseState.Error(r.message())
    }


}