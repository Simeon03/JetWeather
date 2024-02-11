package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.constants.PlaceholderConstants.FLOAT_PLACEHOLDER
import com.example.jetweather.constants.PlaceholderConstants.INT_PLACEHOLDER
import com.example.jetweather.constants.PlaceholderConstants.LIST_FLOAT_PLACEHOLDER
import com.example.jetweather.constants.PlaceholderConstants.LIST_INT_PLACEHOLDER
import com.example.jetweather.constants.PlaceholderConstants.LIST_STRING_24_PLACEHOLDER
import com.example.jetweather.constants.PlaceholderConstants.LIST_STRING_PLACEHOLDER
import com.example.jetweather.constants.PlaceholderConstants.STRING_PLACEHOLDER
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WeatherData(
    val currentTemp: Float,
    val location: String,
    val weatherStatus: Int?,
    val currentMinTemp: Float,
    val currentMaxTemp: Float,
    val weeklyMinTemp: List<Float>,
    val weeklyMaxTemp: List<Float>,
    val dayOfWeek: List<String>,
    val weeklyWeatherCode: List<Int>,
    val hourlyTemperature: List<Float>,
    val hourlyTime: List<String>,
    val hourlyWeatherStatus: List<Int>,
)

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    var weatherData = MutableStateFlow(WeatherData(
        FLOAT_PLACEHOLDER,
        STRING_PLACEHOLDER,
        INT_PLACEHOLDER,
        FLOAT_PLACEHOLDER,
        FLOAT_PLACEHOLDER,
        LIST_FLOAT_PLACEHOLDER,
        LIST_FLOAT_PLACEHOLDER,
        LIST_STRING_PLACEHOLDER,
        LIST_INT_PLACEHOLDER,
        LIST_FLOAT_PLACEHOLDER,
        LIST_STRING_24_PLACEHOLDER,
        LIST_INT_PLACEHOLDER,
    ))
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                // Fetch all data and update weatherData state
                val location = repo.fetchLocation().first()
                val currentTemp = repo.fetchCurrentTemperature().first()
                val weatherStatus = repo.fetchCurrentWeatherStatus().first()
                val currentMinTemp = repo.fetchCurrentMinTemp().first()
                val currentMaxTemp = repo.fetchCurrentMaxTemp().first()
                val weeklyMinTemp = repo.fetchWeeklyMinTemp().first()
                val weeklyMaxTemp = repo.fetchWeeklyMaxTemp().first()
                val dayOfWeek = repo.fetchDayOfWeek().first()
                val weeklyWeatherCode = repo.fetchWeeklyWeatherCode().first()

                val hourlyTemperature = repo.fetchHourlyTemperature().first()
                val hourlyTime = repo.fetchHourlyTime().first()
                val hourlyWeatherStatus = repo.fetchHourlyWeatherStatus().first()

                weatherData.value = WeatherData(
                    currentTemp = currentTemp,
                    location = location,
                    weatherStatus = weatherStatus,
                    currentMinTemp = currentMinTemp,
                    currentMaxTemp = currentMaxTemp,
                    weeklyMinTemp = weeklyMinTemp,
                    weeklyMaxTemp = weeklyMaxTemp,
                    dayOfWeek = dayOfWeek,
                    weeklyWeatherCode = weeklyWeatherCode,
                    hourlyTemperature = hourlyTemperature,
                    hourlyTime = hourlyTime,
                    hourlyWeatherStatus = hourlyWeatherStatus,
                )
            } catch (e: Exception) {
                // Handle errors appropriately
            } finally {
                isLoading.value = false
            }
        }
    }
}