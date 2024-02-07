package com.example.jetweather.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
)

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    var weatherData = MutableStateFlow(WeatherData(
        0f,
        STRING_PLACEHOLDER,
        INT_PLACEHOLDER,
        0f,
        0f,
        listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f),
        listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f),
        LIST_STRING_PLACEHOLDER,
        LIST_INT_PLACEHOLDER,
        LIST_FLOAT_PLACEHOLDER,
        LIST_STRING_24_PLACEHOLDER,
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
                )
            } catch (e: Exception) {
                // Handle errors appropriately
            } finally {
                isLoading.value = false
            }
        }
    }

    companion object {
        private const val STRING_PLACEHOLDER = "Fetch"
        private const val INT_PLACEHOLDER = 0
        private val LIST_INT_PLACEHOLDER = listOf(0, 1, 2, 3, 4, 5, 6)
        private val LIST_STRING_PLACEHOLDER = listOf("2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01")
        private val LIST_FLOAT_PLACEHOLDER = listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        private val LIST_STRING_24_PLACEHOLDER = mutableListOf("0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f", "0f")
    }
}