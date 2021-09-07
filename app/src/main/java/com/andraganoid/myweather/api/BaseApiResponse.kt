package com.andraganoid.myweather.api


import com.andraganoid.myweather.main.App
import com.andraganoid.myweather.model.ResponseError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

abstract class BaseApiResponse {

    private val type = object : TypeToken<ResponseError>() {}.type

    suspend fun <T> apiCall(apiCall: suspend () -> Response<T>): ResponseState<T> {
        if (App.networkStatus) {
            val response = apiCall()
            return try {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ResponseState.Success(body)
                    } else {
                        ResponseState.Error("Something went wrong")
                    }
                } else {
                    val errorResponse: ResponseError? = Gson().fromJson(response.errorBody()!!.charStream(), type)
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
