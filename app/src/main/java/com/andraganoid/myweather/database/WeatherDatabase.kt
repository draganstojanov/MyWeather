package com.andraganoid.myweather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andraganoid.myweather.model.db.QueryModel


@Database(entities = [QueryModel::class], version = 3)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getQueryDao(): WeatherQueryDao
}