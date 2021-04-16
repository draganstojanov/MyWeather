package com.andraganoid.myweather.api

import com.andraganoid.myweather.model.CurrentWeatherResponse
import com.andraganoid.myweather.util.EndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//TODO

    @GET(EndPoint.CURRENT_WEATHER)
    suspend fun getCurrentWeather(

        @Query("id") cityId: Int,
        @Query("appid") appId: String

//        @Query("api_key") access_key: String,
//        @Query("from") from: String,
//        @Query("to") to: String,
//        @Query("amount") amount: Double
    ): Response<CurrentWeatherResponse>

    //  792680
}