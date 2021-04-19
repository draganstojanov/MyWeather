package com.andraganoid.myweather.api

import com.andraganoid.myweather.util.EndPoint
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logB
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCurrentWeather(): ResponseState {
        val r = apiService.getCurrentWeather(key = EndPoint.KEY, query = "Belgrade", aqi = "yes")//todo
        return if (r.isSuccessful) ResponseState.CurrentWeather(r.body()) else ResponseState.Error(r.message())
    }

    suspend fun getAstronomy(): ResponseState {
        val r = apiService.getAstronomy(key = EndPoint.KEY, query = "Belgrade", date = "2021-04-19")//todo
        return if (r.isSuccessful) ResponseState.AstroData(r.body()) else ResponseState.Error(r.message())
    }

    suspend fun getForecast(): ResponseState {
        val r = apiService.getForecast(key = EndPoint.KEY, query = "Belgrade", days = 10, aqi = "yes", alerts = "no")//todo
        return if (r.isSuccessful) ResponseState.ForecastData(r.body()) else ResponseState.Error(r.message())
    }


}