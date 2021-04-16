package com.andraganoid.myweather.api

import com.andraganoid.myweather.util.EndPoint
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logA
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCurrentWeather(): ResponseState {

        val r = apiService.getCurrentWeather(appId = EndPoint.KEY, cityId = 792680)//todo
//        return if (r.isSuccessful) ResponseState.CurrentWeather(r.body()) else ResponseState.Error(r.message())


        logA("getCurrentWeather***$r")

        return if (r.isSuccessful)  ResponseState.Error("RRAAADDIIII") else ResponseState.Error(r.message())
    }


}