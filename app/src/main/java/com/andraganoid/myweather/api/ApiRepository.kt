package com.andraganoid.myweather.api

import com.andraganoid.myweather.main.App
import com.andraganoid.myweather.model.ResponseError
import com.andraganoid.myweather.model.response.AstronomyResponse
import com.andraganoid.myweather.model.response.ForecastResponse
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.EndPoints
import com.andraganoid.myweather.util.ResponseState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject


class ApiRepository @Inject constructor(private val apiService: ApiService, private val gson: Gson) {

    private val type = object : TypeToken<ResponseError>() {}.type

    suspend fun getAstronomy(query: String): ResponseState {
        val response: Response<AstronomyResponse>
        return if (App.networkStatus) {
            apiService.getAstronomy(
                mapOf(
                    "key" to EndPoints.KEY,
                    "q" to query,
                    "date" to DateFormatter.dateQueryToday(),
                )
            ).also { response = it }
            getReturnData(response)
        } else {
            ResponseState.Error("No network")
        }
    }

    suspend fun getForecast(query: String): ResponseState {
        val response: Response<ForecastResponse>
        return if (App.networkStatus) {
            response = apiService.getForecast(
                mapOf(
                    "key" to EndPoints.KEY,
                    "q" to query,
                    "days" to 10,
                    "aqi" to "yes",
                    "alerts" to "no"
                )
            )
            getReturnData(response)
        } else {
            ResponseState.Error("No network")
        }
    }

    private fun getReturnData(response: Response<*>): ResponseState {
        return try {
            if (response.isSuccessful) {
                ResponseState.ResponseData(response.body())
            } else {
                val errorResponse: ResponseError? = gson.fromJson(response.errorBody()!!.charStream(), type)
                ResponseState.Error(errorResponse?.error?.message.toString())
            }
        } catch (exc: Exception) {
            (ResponseState.Error(exc.localizedMessage!!))
        }
    }
}


