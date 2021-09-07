package com.andraganoid.myweather.api

import com.andraganoid.myweather.model.response.AstronomyResponse
import com.andraganoid.myweather.model.response.ForecastResponse
import com.andraganoid.myweather.util.DateFormatter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService, private val gson: Gson) : BaseApiResponse() {


    suspend fun getForecast(query: String): Flow<ResponseState<ForecastResponse>> = flow<ResponseState<ForecastResponse>> {
        emit(apiCall {
            apiService.getForecast(
                mapOf(
                    "key" to EndPoints.KEY,
                    "q" to query,
                    "days" to 10,
                    "aqi" to "yes",
                    "alerts" to "no"
                )
            )
        })
    }.flowOn(Dispatchers.IO)

    suspend fun getAstronomy(query: String): Flow<ResponseState<AstronomyResponse>> = flow<ResponseState<AstronomyResponse>> {
        emit(apiCall {
            apiService.getAstronomy(
                mapOf(
                    "key" to EndPoints.KEY,
                    "q" to query,
                    "date" to DateFormatter.dateQueryToday(),
                )
            )
        })
    }.flowOn(Dispatchers.IO)
}


