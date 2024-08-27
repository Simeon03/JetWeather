package com.example.jetweather.repos

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val TEMPERATURE_UNIT = stringPreferencesKey("temperature_unit")
    }

    val temperatureUnit: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.TEMPERATURE_UNIT] ?: "celsius"
        }

    suspend fun saveTemperatureUnit(unit: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TEMPERATURE_UNIT] = unit
        }
    }
}
