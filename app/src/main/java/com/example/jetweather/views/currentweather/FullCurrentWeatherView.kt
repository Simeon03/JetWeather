package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter.formatTemperatureText
import com.example.jetweather.helper.DataFormatter.formatWeatherCodeText
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun FullCurrentWeatherView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val currentWeatherStatus = formatWeatherCodeText(weatherData.weatherStatus ?: 0)
    val currentTemp = formatTemperatureText(weatherData.currentTemp)
    val currentMinTemp = formatTemperatureText(weatherData.currentMinTemp)
    val currentMaxTemp = formatTemperatureText(weatherData.currentMaxTemp)
    val currentLocation = weatherData.location

    CurrentWeatherStatsView(
        currentLocation = currentLocation,
        currentTemp = currentTemp,
        currentWeatherStatus = currentWeatherStatus,
        currentMinTemp = currentMinTemp,
        currentMaxTemp = currentMaxTemp,
    )
}