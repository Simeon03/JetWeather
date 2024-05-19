package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.main.DefaultWeeklyWeatherRepository
import com.example.jetweather.weatherdata.WeeklyWeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WeeklyWeatherViewModel(
    private val repo: DefaultWeeklyWeatherRepository,
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
                val weeklyMinTemp = repo.fetchWeeklyMinTemp().first()
                val weeklyMaxTemp = repo.fetchWeeklyMaxTemp().first()
                val weeklyDay = repo.fetchWeeklyDay().first()
                val weeklyWeatherStatus = repo.fetchWeeklyWeatherStatus().first()

                weeklyWeatherData.value = WeeklyWeatherData(
                    weeklyMinTemp = weeklyMinTemp,
                    weeklyMaxTemp = weeklyMaxTemp,
                    weeklyDay = weeklyDay,
                    weeklyWeatherStatus = weeklyWeatherStatus,
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}
