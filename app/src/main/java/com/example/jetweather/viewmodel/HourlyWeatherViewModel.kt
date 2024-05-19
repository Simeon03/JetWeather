package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.main.DefaultHourlyWeatherRepository
import com.example.jetweather.weatherdata.HourlyWeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HourlyWeatherViewModel(
    private val repo: DefaultHourlyWeatherRepository,
) : ViewModel() {

    var hourlyWeatherData = MutableStateFlow(HourlyWeatherData())
    private var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val hourlyTemperature = repo.fetchHourlyTemperature().first()
                val hourlyTime = repo.fetchHourlyTime().first()
                val hourlyWeatherStatus = repo.fetchHourlyWeatherStatus().first()
                val hourlyHumidity = repo.fetchHourlyHumidity().first()

                hourlyWeatherData.value = HourlyWeatherData(
                    hourlyTemperature = hourlyTemperature,
                    hourlyTime = hourlyTime,
                    hourlyWeatherStatus = hourlyWeatherStatus,
                    hourlyHumidity = hourlyHumidity,
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}
