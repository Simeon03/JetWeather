package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.main.DefaultCurrentWeatherRepository
import com.example.jetweather.weatherdata.CurrentWeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val repo: DefaultCurrentWeatherRepository,
) : ViewModel() {

    var currentWeatherData = MutableStateFlow(CurrentWeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {

                val currentTemp = repo.fetchCurrentTemperature().first()
                val currentApparentTemp = repo.fetchCurrentApparentTemperature().first()
                val currentWeatherStatus = repo.fetchCurrentWeatherStatus().first()
                val currentLocation = repo.fetchCurrentLocation().first()
                val currentMinTemp = repo.fetchCurrentMinTemp().first()
                val currentMaxTemp = repo.fetchCurrentMaxTemp().first()
                val currentSunriseTime = repo.fetchCurrentSunriseTime().first()
                val currentSunsetTime = repo.fetchCurrentSunsetTime().first()

                currentWeatherData.value = CurrentWeatherData(
                    currentTemp = currentTemp,
                    currentApparentTemp = currentApparentTemp,
                    currentWeatherStatus = currentWeatherStatus,
                    currentLocation = currentLocation,
                    currentMinTemp = currentMinTemp,
                    currentMaxTemp = currentMaxTemp,
                    currentSunriseTime = currentSunriseTime,
                    currentSunsetTime = currentSunsetTime,
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}