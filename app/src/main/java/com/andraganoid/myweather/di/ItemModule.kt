package com.andraganoid.myweather.di

import android.content.Context
import com.andraganoid.myweather.ui.current.ItemBuilder
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