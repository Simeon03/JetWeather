package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun FullCurrentWeatherView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val currentWeatherStatus = DataFormatter.formatWeatherCodeText(weatherData.weatherStatus ?: 0)
    val currentTemp = DataFormatter.formatTemperatureText(weatherData.currentTemp)
    val currentMinTemp = DataFormatter.formatTemperatureText(weatherData.currentMinTemp)
    val currentMaxTemp = DataFormatter.formatTemperatureText(weatherData.currentMaxTemp)
    val currentLocation = weatherData.location

    CurrentWeatherStatsView(
        currentLocation = currentLocation,
        currentTemp = currentTemp,
        currentWeatherStatus = currentWeatherStatus,
        currentMinTemp = currentMinTemp,
        currentMaxTemp = currentMaxTemp,
    )
}
