package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
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
                val hourlyTemperature = repo.fetchTemp().first()
                val hourlyTime = repo.fetchTime().first()
                val hourlyWeatherStatus = repo.fetchWeatherStatus().first()
                val hourlyHumidity = repo.fetchHumidity().first()

                hourlyWeatherData.value = HourlyWeatherData(
                    temperature = hourlyTemperature,
                    time = hourlyTime,
                    weatherStatus = hourlyWeatherStatus,
                    humidity = hourlyHumidity,
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}
