package com.andraganoid.myweather.api

import com.andraganoid.myweather.model.QueryModel
import com.andraganoid.myweather.model.aaa.ErrResp
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.EndPoint
import com.andraganoid.myweather.util.ResponseState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCurrentWeather(): ResponseState {
        val r = apiService.getCurrentWeather(key = EndPoint.KEY, query = "Belgrade", aqi = "yes")//todo
        return if (r.isSuccessful) ResponseState.CurrentWeather(r.body()) else ResponseState.Error(r.message())
    }

    suspend fun getAstronomy(queryModel: QueryModel): ResponseState {
        val r = apiService.getAstronomy(key = EndPoint.KEY, query = queryModel.name.toString(), date = DateFormatter.dateToday())//todo
        return if (r.isSuccessful) ResponseState.AstroData(r.body()) else ResponseState.Error(r.message())
    }

    suspend fun getForecast(queryModel: QueryModel): ResponseState {
        val r = apiService.getForecast(key = EndPoint.KEY, query = queryModel.name.toString(), days = 10, aqi = "yes", alerts = "no")//todo


        val type = object : TypeToken<ErrResp>() {}.type
        var errorResponse: ErrResp? = Gson().fromJson(r.errorBody()!!.charStream(), type)
//  val jsonObj = JSONObject(r.errorBody()!!.charStream().readText())
        return if (r.isSuccessful) ResponseState.ForecastData(r.body()) else  ResponseState.Error(errorResponse?.error?.message.toString())
    }


}


