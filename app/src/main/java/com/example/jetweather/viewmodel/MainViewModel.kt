package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.constants.Placeholder.FLOAT
import com.example.jetweather.constants.Placeholder.INT
import com.example.jetweather.constants.Placeholder.LIST_FLOAT
import com.example.jetweather.constants.Placeholder.LIST_INT
import com.example.jetweather.constants.Placeholder.LIST_STRING
import com.example.jetweather.constants.Placeholder.STRING
import com.example.jetweather.helpers.DataFormatter.formatDay
import com.example.jetweather.helpers.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.helpers.DataFormatter.formatTime
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToIcon
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay
import com.example.jetweather.repos.AllRepos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WeatherData(
    val currentTemp: Float = FLOAT,
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

data class WeatherDataText(
    val currentTemp: String = "0",
    val currentLocation: String = "Unknown",
    val currentWeatherStatus: String = "Unknown",
    val currentMinTemp: String = "0",
    val currentMaxTemp: String = "0",
    val currentSunriseTime: String = "Unknown",
    val currentSunrisePercentage: Float = 0f,
    val currentSunsetTime: String = "Unknown",
    val currentSunsetPercentage: Float = 0f,
    val weeklyMinTemp: List<String> = listOf("0"),
    val weeklyMaxTemp: List<String> = listOf("0"),
    val weeklyDay: List<String> = listOf("Unknown"),
    val weeklyWeatherStatus: List<Int> = listOf(0),
    val hourlyTemperature: List<String> = listOf("0"),
    val hourlyTime: List<String> = listOf("Unknown"),
    val hourlyWeatherStatus: List<Int> = listOf(0),
    val hourlyHumidity: List<String> = listOf("0"),

    )

class MainViewModel(private val repo: AllRepos) : ViewModel() {

    private var weatherData = MutableStateFlow(WeatherData())
    var weatherDataText = MutableStateFlow(WeatherDataText())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val currentTemp = repo.fetchCurrentTemperature().first()
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

                weatherDataText.value = WeatherDataText(
                    currentTemp = formatTemperatureText(currentTemp),
                    currentLocation = currentLocation,
                    currentWeatherStatus = formatWeatherCodeToText(currentWeatherStatus ?: 0),
                    currentMinTemp = formatTemperatureText(currentMinTemp),
                    currentMaxTemp = formatTemperatureText(currentMaxTemp),
                    currentSunriseTime = formatTime(currentSunriseTime),
                    currentSunrisePercentage = getPercentageOfDay(currentSunriseTime),
                    currentSunsetTime = formatTime(currentSunsetTime),
                    currentSunsetPercentage = getPercentageOfDay(currentSunsetTime),
                    weeklyMinTemp = weeklyMinTemp.map { formatTemperatureText(it) },
                    weeklyMaxTemp = weeklyMaxTemp.map { formatTemperatureText(it) },
                    weeklyDay =  weeklyDay.map { formatDay(it) },
                    weeklyWeatherStatus = weeklyWeatherStatus.map { formatWeatherCodeToIcon(it) },
                    hourlyTemperature = hourlyTemperature.map { formatTemperatureText(it) },
                    hourlyTime = hourlyTime,
                    hourlyWeatherStatus = hourlyWeatherStatus,
                    hourlyHumidity = hourlyHumidity.map { formatRelativeHumidityText(it) },
                )
            } catch (e: Exception) {
                // Handle errors appropriately
            } finally {
                isLoading.value = false
            }
        }
    }
}