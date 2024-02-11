package com.example.jetweather.views.currentweather.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter.formatTemperatureText
import com.example.jetweather.helper.DataFormatter.formatWeatherCodeText
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.currentweather.CurrentWeatherStatsView

@Composable
fun FullCurrentWeatherView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val currentWeatherStatus = formatWeatherCodeText(weatherData.currentWeatherStatus ?: 0)
    val currentTemp = formatTemperatureText(weatherData.currentTemp)
    val currentMinTemp = formatTemperatureText(weatherData.currentMinTemp)
    val currentMaxTemp = formatTemperatureText(weatherData.currentMaxTemp)
    val currentLocation = weatherData.currentLocation

    CurrentWeatherStatsView(
        currentLocation = currentLocation,
        currentTemp = currentTemp,
        currentWeatherStatus = currentWeatherStatus,
        currentMinTemp = currentMinTemp,
        currentMaxTemp = currentMaxTemp,
    )
}
