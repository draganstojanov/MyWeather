package com.andraganoid.myweather.util.database.repository

import com.andraganoid.myweather.util.database.model.QueryModel
import com.andraganoid.myweather.util.database.dao.WeatherQueryDao
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val queryDao: WeatherQueryDao) {

    suspend fun saveQuery(query: QueryModel) {
        queryDao.saveQuery(query)
    }

    suspend fun deleteQuery(query: QueryModel) {
        queryDao.deleteQuery(query)
    }

    fun getAllQueries() = queryDao.allQueries()
}
