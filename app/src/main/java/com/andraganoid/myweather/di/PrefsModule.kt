package com.andraganoid.myweather.di

import android.content.Context
import com.andraganoid.prefs.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PrefsModule {

    @Provides
    @Singleton
    fun providesPrefs(@ApplicationContext context: Context) = Prefs(context)

}