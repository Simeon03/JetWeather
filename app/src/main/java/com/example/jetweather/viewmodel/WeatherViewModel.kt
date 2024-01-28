package com.example.jetweather.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    var currentTempText = MutableStateFlow("Fetch")
    var currentLocationText = MutableStateFlow("Location")
    var currentWeatherStatusText = MutableStateFlow("Status")
    var currentMinTempText = MutableStateFlow(0)
    var currentMaxTempText = MutableStateFlow(0)
    var weeklyMinTempText = MutableStateFlow(listOf(0, 1, 2, 3, 4, 5, 6))
    var weeklyMaxTempText = MutableStateFlow(listOf(0, 1, 2, 3, 4, 5, 6))
    var dayOfWeek = MutableStateFlow(listOf("2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01"))
    var weeklyWeatherCode = MutableStateFlow(listOf(0, 1, 2, 3, 4, 5, 6))

    init {
        fetchCurrentTemperature()
        fetchLocationText()
        fetchCurrentWeatherStatus()
        fetchCurrentMinTemp()
        fetchCurrentMaxTemp()
        fetchWeeklyMinTemp()
        fetchWeeklyMaxTemp()
        fetchDayOfWeek()
        fetchWeeklyWeatherCode()
    }

    private fun fetchLocationText() {
        viewModelScope.launch {
            repo.fetchLocationText().collect {
                currentLocationText.value = it
            }
        }
    }

    private fun fetchCurrentTemperature() {
        viewModelScope.launch {
            repo.fetchCurrentTemperatureText().collect {
                currentTempText.value = it
            }
        }
    }

    private fun fetchCurrentWeatherStatus() {
        viewModelScope.launch {
            repo.fetchCurrentWeatherStatusText().collect {
                currentWeatherStatusText.value = it
            }
        }
    }

    private fun fetchCurrentMinTemp() {
        viewModelScope.launch {
            repo.fetchCurrentMinTempText().collect() {
                currentMinTempText.value = it
            }
        }
    }

    private fun fetchCurrentMaxTemp() {
        viewModelScope.launch {
            repo.fetchCurrentMaxTempText().collect() {
                currentMaxTempText.value = it
            }
        }
    }

    private fun fetchWeeklyMinTemp() {
        viewModelScope.launch {
            repo.fetchWeeklyMinTempText().collect {
                weeklyMinTempText.value = it
            }
        }
    }

    private fun fetchWeeklyMaxTemp() {
        viewModelScope.launch {
            repo.fetchWeeklyMaxTempText().collect {
                weeklyMaxTempText.value = it
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchDayOfWeek() {
        viewModelScope.launch {
            repo.fetchDayOfWeek().collect {
                dayOfWeek.value = it
            }
        }
    }

    private fun fetchWeeklyWeatherCode() {
        viewModelScope.launch {
            repo.fetchWeeklyWeatherCode().collect {
                weeklyWeatherCode.value = it
            }
        }
    }
}