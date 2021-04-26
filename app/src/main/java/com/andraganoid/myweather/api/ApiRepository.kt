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


class ApiRepository @Inject constructor(private val apiService: ApiService) {

    private val type = object : TypeToken<ResponseError>() {}.type

    suspend fun getAstronomy(query: String): ResponseState {

        var response: Response<AstronomyResponse>? = null

        if (App.networkStatus) {

            response = apiService.getAstronomy(
                mapOf(
                    "key" to EndPoints.KEY,
                    "q" to query,
                    "date" to DateFormatter.dateQueryToday(),
                )
            )

            return try {
                if (response.isSuccessful) {
                    ResponseState.AstronomyData(response.body())
                } else {
                    val errorResponse: ResponseError? = Gson().fromJson(response.errorBody()!!.charStream(), type)
                    ResponseState.Error(errorResponse?.error?.message.toString())
                }
            } catch (exc: Exception) {
                (ResponseState.Error(exc.localizedMessage!!))
            }
        } else {
            return ResponseState.Error("No network")
        }
    }

    suspend fun getForecast(query: String): ResponseState {

        var response: Response<ForecastResponse>? = null

        if (App.networkStatus) {
            response = apiService.getForecast(
                mapOf(
                    "key" to EndPoints.KEY,
                    "q" to query,
                    "days" to 10,
                    "aqi" to "yes",
                    "alerts" to "no"
                )
            )
            return try {
                if (response.isSuccessful) {
                    ResponseState.ForecastData(response.body())
                } else {
                    val errorResponse: ResponseError? = Gson().fromJson(response.errorBody()!!.charStream(), type)
                    ResponseState.Error(errorResponse?.error?.message.toString())
                }
            } catch (exc: Exception) {
                (ResponseState.Error(exc.localizedMessage!!))
            }


        } else {
            return ResponseState.Error("No network")
        }
    }
}


