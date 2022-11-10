package com.andraganoid.myweather.util.di

import android.content.Context
import androidx.room.Room
import com.andraganoid.myweather.util.database.WeatherDatabase
import com.andraganoid.myweather.util.database.constant.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            WeatherDatabase::class.java,
           DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideQueryDao(db: WeatherDatabase) = db.getQueryDao()
}