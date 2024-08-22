package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.example.jetweather.weatherdata.HourlyWeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HourlyWeatherViewModel(
    private val hourlyWeather: DefaultHourlyWeatherRepository,
) : ViewModel() {

    var hourlyWeatherData = MutableStateFlow(HourlyWeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                hourlyWeather.apply {
                    val hourlyTemperature = fetchTemp().first()
                    val hourlyTime = fetchTime().first()
                    val hourlyWeatherStatus = fetchWeatherStatus().first()
                    val hourlyHumidity = fetchHumidity().first()

                    hourlyWeatherData.value = HourlyWeatherData(
                        temperature = hourlyTemperature,
                        time = hourlyTime,
                        weatherStatus = hourlyWeatherStatus,
                        humidity = hourlyHumidity,
                    )
                }
            } finally {
                isLoading.value = false
            }
        }
    }
}
