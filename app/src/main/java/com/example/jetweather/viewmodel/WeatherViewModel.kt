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
    val weatherStatus: String?,
    val currentMinTemp: Int,
    val currentMaxTemp: Int,
    val weeklyMinTemp: List<Int>,
    val weeklyMaxTemp: List<Int>,
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
        STRING_PLACEHOLDER,
        INT_PLACEHOLDER,
        INT_PLACEHOLDER,
        LIST_INT_PLACEHOLDER,
        LIST_INT_PLACEHOLDER,
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
                val locationText = repo.fetchLocationText().first()
                val currentTemp = repo.fetchCurrentTemperature().first()
                val weatherStatusText = repo.fetchCurrentWeatherStatusText().first()
                val currentMinTempText = repo.fetchCurrentMinTempText().first()
                val currentMaxTempText = repo.fetchCurrentMaxTempText().first()
                val weeklyMinTempText = repo.fetchWeeklyMinTempText().first()
                val weeklyMaxTempText = repo.fetchWeeklyMaxTempText().first()
                val dayOfWeekText = repo.fetchDayOfWeek().first()
                val weeklyWeatherCodeText = repo.fetchWeeklyWeatherCode().first()

                val hourlyTemperatureText = repo.fetchHourlyTemperature().first()
                val hourlyTimeText = repo.fetchHourlyTime().first()

                weatherData.value = WeatherData(
                    currentTemp = currentTemp,
                    location = locationText,
                    weatherStatus = weatherStatusText,
                    currentMinTemp = currentMinTempText,
                    currentMaxTemp = currentMaxTempText,
                    weeklyMinTemp = weeklyMinTempText,
                    weeklyMaxTemp = weeklyMaxTempText,
                    dayOfWeek = dayOfWeekText,
                    weeklyWeatherCode = weeklyWeatherCodeText,
                    hourlyTemperature = hourlyTemperatureText,
                    hourlyTime = hourlyTimeText,
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