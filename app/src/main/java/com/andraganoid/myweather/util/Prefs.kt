package com.andraganoid.myweather.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class Prefs @Inject constructor(val context: Context) {

    companion object {
        private const val PREF_NAME = "com.andraganoid.myweather.SHARED_PREFERENCES"
        private const val LAST_CALLED_QUERY = "lastCalledQuery"
    }

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveLastCalledQuery(query: String) {
        sharedPreferences.edit().putString(LAST_CALLED_QUERY, query).apply()
    }

    fun getLastCalledQuery(): String? = sharedPreferences.getString(LAST_CALLED_QUERY, "")

}