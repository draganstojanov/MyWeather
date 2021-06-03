package com.andraganoid.myweather.model


sealed class ResponseState {
    data class Loading(val loaderMsg: String) : ResponseState()
    data class Error(val errorMsg: String) : ResponseState()
    data class ResponseData(val responseData: Any?) : ResponseState()
}