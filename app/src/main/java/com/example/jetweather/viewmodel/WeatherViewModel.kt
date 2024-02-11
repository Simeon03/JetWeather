package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.constants.PlaceholderConstants.FLOAT
import com.example.jetweather.constants.PlaceholderConstants.INT
import com.example.jetweather.constants.PlaceholderConstants.LIST_FLOAT
import com.example.jetweather.constants.PlaceholderConstants.LIST_INT
import com.example.jetweather.constants.PlaceholderConstants.LIST_STRING
import com.example.jetweather.constants.PlaceholderConstants.STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WeatherData(
    val currentTemp: Float = FLOAT,
    val location: String = STRING,
    val weatherStatus: Int? = INT,
    val currentMinTemp: Float = FLOAT,
    val currentMaxTemp: Float = FLOAT,
    val weeklyMinTemp: List<Float> = LIST_FLOAT,
    val weeklyMaxTemp: List<Float> = LIST_FLOAT,
    val dayOfWeek: List<String> = LIST_STRING,
    val weeklyWeatherCode: List<Int> = LIST_INT,
    val hourlyTemperature: List<Float> = LIST_FLOAT,
    val hourlyTime: List<String> = LIST_STRING,
    val hourlyWeatherStatus: List<Int> = LIST_INT,
    val sunriseTime: List<String> = LIST_STRING,
    val sunsetTime: List<String> = LIST_STRING,
    val hourlyHumidity: List<Int> = LIST_INT,
)

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {

    var weatherData = MutableStateFlow(WeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
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
                val hourlyHumidity = repo.fetchHourlyHumidity().first()

                val sunriseTime = repo.fetchSunriseTime().first()
                val sunsetTime = repo.fetchSunsetTime().first()

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
                    sunriseTime = sunriseTime,
                    sunsetTime = sunsetTime,
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