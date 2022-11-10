package com.andraganoid.myweather.util.di

import android.content.Context
import com.andraganoid.myweather.current.ItemBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ItemModule {

    @Provides
    @Singleton
    fun providesItem(@ApplicationContext context: Context) = ItemBuilder(context)

}