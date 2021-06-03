package com.andraganoid.myweather.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")


class Datastore @Inject constructor(val context: Context) {

    companion object {
        val LAST_QUERY_CALLED = stringPreferencesKey("lastQueryCalled")
    }

    val lastQueryCalled: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[LAST_QUERY_CALLED] ?: ""
    }

    suspend fun saveLastQueryCalled(lastQuery: String) {
        context.dataStore.edit { settings ->
            settings[LAST_QUERY_CALLED] = lastQuery
        }
    }
}