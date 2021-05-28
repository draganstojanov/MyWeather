package com.andraganoid.myweather.util


sealed class ResponseState {
    data class Loading(val loaderMsg: String) : ResponseState()
    data class Error(val errorMsg: String) : ResponseState()
    data class ResponseData(val responseData: Any?) : ResponseState()
}