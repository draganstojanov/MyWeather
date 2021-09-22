package com.andraganoid.myweather.api


import com.andraganoid.connectivity.ConnectivityState.Companion.connectivityStatus
import com.andraganoid.myweather.model.ResponseError
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
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
                    val errorResponse: ResponseError? = Json.decodeFromString(response.errorBody().toString())
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
