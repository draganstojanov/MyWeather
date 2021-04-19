package com.andraganoid.myweather.api


import com.andraganoid.myweather.model.AstronomyResponse
import com.andraganoid.myweather.model.CurrentResponse
import com.andraganoid.myweather.model.ForecastResponse
import com.andraganoid.myweather.util.EndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(EndPoint.CURRENT)
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("aqi") aqi: String
    ): Response<CurrentResponse>

    @GET(EndPoint.ASTRONOMY)
    suspend fun getAstronomy(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("dt") date: String,
    ): Response<AstronomyResponse>

    @GET(EndPoint.FORECAST)
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String,
        @Query("days") days: Int,
    ): Response<ForecastResponse>

}