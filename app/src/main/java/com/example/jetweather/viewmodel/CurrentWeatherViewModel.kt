package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultCurrentWeatherRepository
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

                val currentTemp = repo.fetchTemp().first()
                val currentApparentTemp = repo.fetchApparentTemp().first()
                val currentWeatherStatus = repo.fetchWeatherStatus().first()
                val currentLocation = repo.fetchCurrentLocation().first()
                val currentMinTemp = repo.fetchMinTemp().first()
                val currentMaxTemp = repo.fetchMaxTemp().first()
                val currentSunriseTime = repo.fetchSunriseTime().first()
                val currentSunsetTime = repo.fetchSunsetTime().first()

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
            } finally {
                isLoading.value = false
            }
        }
    }
}