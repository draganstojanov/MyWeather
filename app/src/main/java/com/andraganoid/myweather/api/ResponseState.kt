package com.andraganoid.myweather.api

sealed class ResponseState<T>(
    val data: Any? = null,
    val message: String? = null
) {
    class Success<T>(data: Any) : ResponseState<T>(data)
    class Error<T>(message: String, data: T? = null) : ResponseState<T>(data, message)
    class Loading<T> : ResponseState<T>()
}