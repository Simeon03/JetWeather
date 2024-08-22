package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultCurrentHourWeatherRepository
import com.example.jetweather.weatherdata.CurrentHourWeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CurrentHourWeatherViewModel(
    private val currentWeather: DefaultCurrentHourWeatherRepository,
) : ViewModel() {

    var currentHourWeatherData = MutableStateFlow(CurrentHourWeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                currentWeather.apply {
                    val currentHourCloudCover = fetchCloudCover().first()
                    val currentHourVisibility = fetchVisibility().first()
                    val currentHourUvIndex = fetchUvIndex().first()

                    currentHourWeatherData.value = CurrentHourWeatherData(
                        cloudCover = currentHourCloudCover,
                        visibility = currentHourVisibility,
                        uvIndex = currentHourUvIndex,
                    )
                }

            } finally {
                isLoading.value = false
            }
        }
    }
}