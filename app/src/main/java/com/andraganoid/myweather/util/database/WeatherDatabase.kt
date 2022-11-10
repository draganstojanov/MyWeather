package com.andraganoid.myweather.util.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andraganoid.myweather.util.database.model.QueryModel
import com.andraganoid.myweather.util.database.dao.WeatherQueryDao


@Database(entities = [QueryModel::class], version = 3, exportSchema = false)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getQueryDao(): WeatherQueryDao
}