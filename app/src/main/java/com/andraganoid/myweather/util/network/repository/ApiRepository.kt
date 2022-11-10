package com.andraganoid.myweather.util.network.repository

import com.andraganoid.myweather.util.network.model.response.AstronomyResponse
import com.andraganoid.myweather.util.network.model.response.ForecastResponse
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.network.ApiService
import com.andraganoid.myweather.util.network.BaseApiResponse
import com.andraganoid.myweather.util.network.constant.EndPoints
import com.andraganoid.myweather.util.network.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {


    suspend fun getForecast(query: String): Flow<ResponseState<ForecastResponse>> = flow {
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

    suspend fun getAstronomy(query: String): Flow<ResponseState<AstronomyResponse>> = flow {
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


