package com.example.jetweather.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WeatherData(
    val currentTemp: String,
    val location: String,
    val weatherStatus: String,
    val currentMinTemp: Int,
    val currentMaxTemp: Int,
    val weeklyMinTemp: List<Int>,
    val weeklyMaxTemp: List<Int>,
    val dayOfWeek: List<String>,
    val weeklyWeatherCode: List<Int>
)

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    var weatherData = MutableStateFlow(WeatherData(
        "Fetch",
        "Location",
        "Status",
        0,
        0,
        listOf(0, 1, 2, 3, 4, 5, 6),
        listOf(0, 1, 2, 3, 4, 5, 6),
        listOf("2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01", "2023-02-01"),
        listOf(0, 1, 2, 3, 4, 5, 6),
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
                val currentTempText = repo.fetchCurrentTemperatureText().first()
                val weatherStatusText = repo.fetchCurrentWeatherStatusText().first()
                val currentMinTempText = repo.fetchCurrentMinTempText().first()
                val currentMaxTempText = repo.fetchCurrentMaxTempText().first()
                val weeklyMinTempText = repo.fetchWeeklyMinTempText().first()
                val weeklyMaxTempText = repo.fetchWeeklyMaxTempText().first()
                val dayOfWeekText = repo.fetchDayOfWeek().first()
                val weeklyWeatherCodeText = repo.fetchWeeklyWeatherCode().first()

                weatherData.value = WeatherData(
                    currentTemp = currentTempText,
                    location = locationText,
                    weatherStatus = weatherStatusText,
                    currentMinTemp = currentMinTempText,
                    currentMaxTemp = currentMaxTempText,
                    weeklyMinTemp = weeklyMinTempText,
                    weeklyMaxTemp = weeklyMaxTempText,
                    dayOfWeek = dayOfWeekText,
                    weeklyWeatherCode = weeklyWeatherCodeText,
                )
            } catch (e: Exception) {
                // Handle errors appropriately
            } finally {
                isLoading.value = false
            }
        }
    }
}