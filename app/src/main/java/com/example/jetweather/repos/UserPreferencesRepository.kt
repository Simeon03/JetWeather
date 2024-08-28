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
        val THEME_PREFERENCE = stringPreferencesKey("theme_preference") // New key for theme preference
    }

    // Flow to get the temperature unit
    val temperatureUnit: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.TEMPERATURE_UNIT] ?: "celsius"
        }

    // Flow to get the theme preference
    val themePreference: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.THEME_PREFERENCE] ?: "system_default"
        }

    // Function to save the temperature unit
    suspend fun saveTemperatureUnit(unit: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TEMPERATURE_UNIT] = unit
        }
    }

    // Function to save the theme preference
    suspend fun saveThemePreference(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_PREFERENCE] = theme
        }
    }
}
