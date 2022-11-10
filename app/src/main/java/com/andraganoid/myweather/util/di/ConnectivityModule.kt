package com.andraganoid.myweather.util.di

import android.content.Context
import com.andraganoid.connectivity.ConnectivityState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ConnectivityModule {

    @Provides
    @Singleton
    fun providesConnectivity(@ApplicationContext context: Context) = ConnectivityState(context)

}