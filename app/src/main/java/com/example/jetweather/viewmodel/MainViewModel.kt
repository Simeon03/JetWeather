package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.constants.Placeholder.FLOAT
import com.example.jetweather.constants.Placeholder.INT
import com.example.jetweather.constants.Placeholder.LIST_FLOAT
import com.example.jetweather.constants.Placeholder.LIST_INT
import com.example.jetweather.constants.Placeholder.LIST_STRING
import com.example.jetweather.constants.Placeholder.STRING
import com.example.jetweather.repos.AllRepos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WeatherData(
    val currentTemp: Float = FLOAT,
    val currentApparentTemp: Float = FLOAT,
    val currentLocation: String = STRING,
    val currentWeatherStatus: Int? = INT,
    val currentMinTemp: Float = FLOAT,
    val currentMaxTemp: Float = FLOAT,
    val currentSunriseTime: String = STRING,
    val currentSunsetTime: String = STRING,
    val weeklyMinTemp: List<Float> = LIST_FLOAT,
    val weeklyMaxTemp: List<Float> = LIST_FLOAT,
    val weeklyDay: List<String> = LIST_STRING,
    val weeklyWeatherStatus: List<Int> = LIST_INT,
    val hourlyTemperature: List<Float> = LIST_FLOAT,
    val hourlyTime: List<String> = LIST_STRING,
    val hourlyWeatherStatus: List<Int> = LIST_INT,
    val hourlyHumidity: List<Int> = LIST_INT,
)

class MainViewModel(private val repo: AllRepos) : ViewModel() {

    var weatherData = MutableStateFlow(WeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val currentTemp = repo.fetchCurrentTemperature().first()
                val currentApparentTemp = repo.fetchCurrentApparentTemperature().first()
                val currentLocation = repo.fetchCurrentLocation().first()
                val currentWeatherStatus = repo.fetchCurrentWeatherStatus().first()
                val currentMinTemp = repo.fetchCurrentMinTemp().first()
                val currentMaxTemp = repo.fetchCurrentMaxTemp().first()
                val currentSunriseTime = repo.fetchCurrentSunriseTime().first()
                val currentSunsetTime = repo.fetchCurrentSunsetTime().first()

                val weeklyMinTemp = repo.fetchWeeklyMinTemp().first()
                val weeklyMaxTemp = repo.fetchWeeklyMaxTemp().first()
                val weeklyDay = repo.fetchWeeklyDay().first()
                val weeklyWeatherStatus = repo.fetchWeeklyWeatherStatus().first()

                val hourlyTemperature = repo.fetchHourlyTemperature().first()
                val hourlyTime = repo.fetchHourlyTime().first()
                val hourlyWeatherStatus = repo.fetchHourlyWeatherStatus().first()
                val hourlyHumidity = repo.fetchHourlyHumidity().first()

                weatherData.value = WeatherData(
                    currentTemp = currentTemp,
                    currentApparentTemp = currentApparentTemp,
                    currentLocation = currentLocation,
                    currentWeatherStatus = currentWeatherStatus,
                    currentMinTemp = currentMinTemp,
                    currentMaxTemp = currentMaxTemp,
                    currentSunriseTime = currentSunriseTime,
                    currentSunsetTime = currentSunsetTime,
                    weeklyMinTemp = weeklyMinTemp,
                    weeklyMaxTemp = weeklyMaxTemp,
                    weeklyDay = weeklyDay,
                    weeklyWeatherStatus = weeklyWeatherStatus,
                    hourlyTemperature = hourlyTemperature,
                    hourlyTime = hourlyTime,
                    hourlyWeatherStatus = hourlyWeatherStatus,
                    hourlyHumidity = hourlyHumidity,
                )
            } catch (e: Exception) {
                // Handle errors appropriately
            } finally {
                isLoading.value = false
            }
        }
    }
}