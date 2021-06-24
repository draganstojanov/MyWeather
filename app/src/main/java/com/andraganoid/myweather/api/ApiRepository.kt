package com.andraganoid.myweather.api

import com.andraganoid.myweather.main.App
import com.andraganoid.myweather.model.ResponseError
import com.andraganoid.myweather.model.response.AstronomyResponse
import com.andraganoid.myweather.model.response.ForecastResponse
import com.andraganoid.myweather.util.DateFormatter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject


class ApiRepository @Inject constructor(private val apiService: ApiService, private val gson: Gson) {

    private val type = object : TypeToken<ResponseError>() {}.type

    suspend fun getAstronomy(query: String): ResponseState = checkResponse(
        apiService.getAstronomy(
            mapOf(
                "key" to EndPoints.KEY,
                "q" to query,
                "date" to DateFormatter.dateQueryToday(),
            )
        )
    )

    suspend fun getForecast(query: String): ResponseState = checkResponse(
        apiService.getForecast(
            mapOf(
                "key" to EndPoints.KEY,
                "q" to query,
                "days" to 10,
                "aqi" to "yes",
                "alerts" to "no"
            )
        )
    )

    private fun checkResponse(response: Response<*>): ResponseState {
        if (App.networkStatus) {
            return try {
                if (response.isSuccessful) {
                    when (response.body()) {
                        is AstronomyResponse -> ResponseState.AstronomyData(response.body() as AstronomyResponse)
                        is ForecastResponse -> ResponseState.ForecastData(response.body() as ForecastResponse)
                        else -> ResponseState.Error("Something went wrong")
                    }
                } else {
                    val errorResponse: ResponseError? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    ResponseState.Error(errorResponse?.error?.message.toString())
                }
            } catch (exc: Exception) {
                ResponseState.Error(exc.localizedMessage!!)
            }
        } else {
            return ResponseState.Error("No network")
        }
    }

}


