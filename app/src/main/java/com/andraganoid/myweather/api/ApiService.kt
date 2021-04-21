package com.andraganoid.myweather.api


import com.andraganoid.myweather.model.AstronomyResponse
import com.andraganoid.myweather.model.CurrentResponse
import com.andraganoid.myweather.model.ForecastResponse
import com.andraganoid.myweather.util.EndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET(EndPoint.CURRENT)
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("aqi") aqi: String
    ): Response<CurrentResponse>

    @JvmSuppressWildcards
    @GET(EndPoint.ASTRONOMY)
    suspend fun getAstronomy(@QueryMap query: Map<String, Any>): Response<AstronomyResponse>

    @JvmSuppressWildcards
    @GET(EndPoint.FORECAST)
    suspend fun getForecast(@QueryMap query: Map<String, Any>): Response<ForecastResponse>

}