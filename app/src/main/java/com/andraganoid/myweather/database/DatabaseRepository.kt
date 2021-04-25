package com.andraganoid.myweather.database

import com.andraganoid.myweather.model.db.QueryModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val queryDao: WeatherQueryDao) {

    suspend fun saveQuery(query: QueryModel) {
        queryDao.saveQuery(query)
    }

    suspend fun deleteQuery(query: QueryModel) {
        queryDao.deleteQuery(query)
    }

    fun getAllQuerys() = queryDao.allQuerys()
}
