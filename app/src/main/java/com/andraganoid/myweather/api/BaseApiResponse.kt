package com.andraganoid.myweather.api


import com.andraganoid.connectivity.ConnectivityState.Companion.connectivityStatus
import com.andraganoid.myweather.model.ResponseError
import com.andraganoid.myweather.util.parseErrJsonResponse

import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> apiCall(apiCall: suspend () -> Response<T>): ResponseState<T> {
        return if (connectivityStatus) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ResponseState.Success(body)
                    } else {
                        ResponseState.Error("Something went wrong")
                    }
                } else {
                    val errorResponse: ResponseError? =
                        response.parseErrJsonResponse<ResponseError>()
                    return if (errorResponse != null) ResponseState.Error(errorResponse.error?.message.toString()) else ResponseState.Error(
                        "Something went wrong"
                    )
                }
            } catch (exc: Exception) {
                ResponseState.Error(exc.localizedMessage!!)
            }
        } else {
            ResponseState.Error("No network")
        }
    }
}
