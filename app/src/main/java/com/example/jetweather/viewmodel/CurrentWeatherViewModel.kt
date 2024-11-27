package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.UserPreferencesRepo
import com.example.jetweather.repos.sub.DefaultCurrentWeatherRepository
import com.example.jetweather.weatherdata.CurrentWeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val currentWeather: DefaultCurrentWeatherRepository,
    private val userPreferencesRepo: UserPreferencesRepo
) : ViewModel() {

    val temperatureUnit: Flow<String> = userPreferencesRepo.temperatureUnit
    val themePreference: Flow<String> = userPreferencesRepo.themePreference
    var currentWeatherData = MutableStateFlow(CurrentWeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                currentWeather.apply {
                    val currentTemp = fetchTemp().first()
                    val currentApparentTemp = fetchApparentTemp().first()
                    val currentWeatherStatus = fetchWeatherStatus().first()
                    val currentLocation = fetchCurrentCity().first()
                    val currentMinTemp = fetchMinTemp().first()
                    val currentMaxTemp = fetchMaxTemp().first()
                    val currentSunriseTime = fetchSunriseTime().first()
                    val currentSunsetTime = fetchSunsetTime().first()

                    currentWeatherData.value = CurrentWeatherData(
                        temp = currentTemp,
                        apparentTemp = currentApparentTemp,
                        weatherStatus = currentWeatherStatus,
                        location = currentLocation,
                        minTemp = currentMinTemp,
                        maxTemp = currentMaxTemp,
                        sunriseTime = currentSunriseTime,
                        sunsetTime = currentSunsetTime,
                    )
                }

            } finally {
                isLoading.value = false
            }
        }
    }

    fun saveTemperatureUnit(unit: String) {
        viewModelScope.launch {
            userPreferencesRepo.saveTemperatureUnit(unit)
        }
    }

    fun saveThemePreference(theme: String) {
        viewModelScope.launch {
            userPreferencesRepo.saveThemePreference(theme)
        }
    }
}