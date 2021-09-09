package com.andraganoid.myweather.util

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

inline fun <reified T> Response<*>.parseErrJsonResponse(): T? {
    val parser = moshi.adapter(T::class.java)
    val response = errorBody()?.string()
    if (response != null)
        try {
            return parser.fromJson(response)
        } catch (e: JsonDataException) {
            e.printStackTrace()
        }
    return null
}
