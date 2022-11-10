package com.andraganoid.myweather.util.network.model

data class ResponseError(val error: Error? = null)

data class Error(val code: Int? = null, val message: String? = null)

