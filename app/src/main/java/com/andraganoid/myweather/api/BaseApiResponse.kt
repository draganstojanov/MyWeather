package com.andraganoid.myweather.api


import com.andraganoid.myweather.main.App
import com.andraganoid.myweather.model.ResponseError
import com.andraganoid.myweather.util.parseErrJsonResponse
import retrofit2.Response

abstract class BaseApiResponse {

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
                    val errorResponse: ResponseError? = response.parseErrJsonResponse<ResponseError>()
                    return if (errorResponse != null) ResponseState.Error(errorResponse.error?.message.toString()) else ResponseState.Error("Something went wrong")
                }
            } catch (exc: Exception) {
                ResponseState.Error(exc.localizedMessage!!)
            }
        } else {
            return ResponseState.Error("No network")
        }
    }
}
