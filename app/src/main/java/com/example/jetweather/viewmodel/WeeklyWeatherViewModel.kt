package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.example.jetweather.weatherdata.WeeklyWeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WeeklyWeatherViewModel(
    private val weeklyWeather: DefaultWeeklyWeatherRepository,
) : ViewModel() {

    var weeklyWeatherData = MutableStateFlow(WeeklyWeatherData())
    private var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                weeklyWeather.apply {
                    val weeklyMinTemp = fetchMinTemp().first()
                    val weeklyMaxTemp = fetchMaxTemp().first()
                    val weeklyDay = fetchDay().first()
                    val weeklyWeatherStatus = fetchWeatherStatus().first()

                    weeklyWeatherData.value = WeeklyWeatherData(
                        minTemp = weeklyMinTemp,
                        maxTemp = weeklyMaxTemp,
                        day = weeklyDay,
                        weatherStatus = weeklyWeatherStatus,
                    )
                }
            } finally {
                isLoading.value = false
            }
        }
    }
}
