package com.andraganoid.myweather.di

import android.content.Context
import androidx.room.Room
import com.andraganoid.myweather.database.DB
import com.andraganoid.myweather.database.WeatherDatabase
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
            DB.NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideQueryDao(db: WeatherDatabase) = db.getQueryDao()
}