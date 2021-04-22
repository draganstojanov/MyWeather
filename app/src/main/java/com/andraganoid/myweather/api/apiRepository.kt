package com.andraganoid.myweather.api

import com.andraganoid.myweather.model.ResponseError
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.EndPoint
import com.andraganoid.myweather.util.ResponseState
import com.andraganoid.myweather.util.logC
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class apiRepository @Inject constructor(private val apiService: ApiService) {

    private val type = object : TypeToken<ResponseError>() {}.type

    suspend fun getCurrentWeather(): ResponseState {
        val r = apiService.getCurrentWeather(key = EndPoint.KEY, query = "Belgrade", aqi = "yes")//todo
        return if (r.isSuccessful) ResponseState.CurrentWeather(r.body()) else ResponseState.Error(r.message())
    }

    suspend fun getAstronomy(query: String): ResponseState {

        logC(DateFormatter.dateQueryToday())

        val response = apiService.getAstronomy(
            mapOf(
                "key" to EndPoint.KEY,
                "q" to query,
                "date" to DateFormatter.dateQueryToday(),
            )
        )
        return try {
            if (response.isSuccessful) {
                ResponseState.AstroData(response.body())//todo save last to prefs
            } else {
                val errorResponse: ResponseError? = Gson().fromJson(response.errorBody()!!.charStream(), type)
                ResponseState.Error(errorResponse?.error?.message.toString())
            }
        } catch (exc: Exception) {
            (ResponseState.Error(exc.localizedMessage!!))
        }
    }

    suspend fun getForecast(query: String): ResponseState {
        val response = apiService.getForecast(
            mapOf(
                "key" to EndPoint.KEY,
                "q" to query,
                "days" to 10,
                "aqi" to "yes",
                "alerts" to "no"
            )
        )
        return try {
            if (response.isSuccessful) {
                ResponseState.ForecastData(response.body())//todo save last to prefs
            } else {
                val errorResponse: ResponseError? = Gson().fromJson(response.errorBody()!!.charStream(), type)
                ResponseState.Error(errorResponse?.error?.message.toString())
            }
        } catch (exc: Exception) {
            (ResponseState.Error(exc.localizedMessage!!))
        }
    }

}


