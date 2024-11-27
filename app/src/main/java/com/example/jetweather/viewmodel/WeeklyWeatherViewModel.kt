package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.jetweather.core.weatherdata.WeeklyWeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyWeatherViewModel @Inject constructor(
    private val weeklyWeather: DefaultWeeklyWeatherRepository
) : ViewModel() {

    var weeklyWeatherData = MutableStateFlow(WeeklyWeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData() {
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
