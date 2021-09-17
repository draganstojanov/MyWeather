package com.andraganoid.myweather.di

import android.content.Context
import com.andraganoid.net.NetCheck
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @Singleton
    fun providesNet(@ApplicationContext context: Context) = NetCheck(context)

}