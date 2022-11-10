package com.andraganoid.myweather.util.network


import com.andraganoid.myweather.util.network.model.response.AstronomyResponse
import com.andraganoid.myweather.util.network.model.response.ForecastResponse
import com.andraganoid.myweather.util.network.constant.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @JvmSuppressWildcards
    @GET(EndPoints.ASTRONOMY)
    suspend fun getAstronomy(@QueryMap query: Map<String, Any>): Response<AstronomyResponse>

    @JvmSuppressWildcards
    @GET(EndPoints.FORECAST)
    suspend fun getForecast(@QueryMap query: Map<String, Any>): Response<ForecastResponse>

}