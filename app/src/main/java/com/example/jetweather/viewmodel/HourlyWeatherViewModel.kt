package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.jetweather.core.weatherdata.HourlyWeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HourlyWeatherViewModel @Inject constructor(
    private val hourlyWeather: DefaultHourlyWeatherRepository
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
                    val hourlyPrecipitationProbability = fetchPrecipitationProbability().first()

                    hourlyWeatherData.value = HourlyWeatherData(
                        temperature = hourlyTemperature,
                        time = hourlyTime,
                        weatherStatus = hourlyWeatherStatus,
                        humidity = hourlyHumidity,
                        hourlyPrecipitationProbability = hourlyPrecipitationProbability
                    )
                }
            } finally {
                isLoading.value = false
            }
        }
    }
}
