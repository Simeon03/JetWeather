package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.viewmodel.MainViewModel
import com.example.jetweather.views.currentweather.layouts.CurrentWeatherInfo

@Composable
fun CurrentWeatherView(viewModel: MainViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val currentWeatherStatus = formatWeatherCodeToText(weatherData.currentWeatherStatus ?: 0)
    val currentTemp = formatTemperatureText(weatherData.currentTemp)
    val currentMinTemp = formatTemperatureText(weatherData.currentMinTemp)
    val currentMaxTemp = formatTemperatureText(weatherData.currentMaxTemp)
    val currentLocation = weatherData.currentLocation

    CurrentWeatherInfo(
        currentLocation = currentLocation,
        currentTemp = currentTemp,
        currentWeatherStatus = currentWeatherStatus,
        currentMinTemp = currentMinTemp,
        currentMaxTemp = currentMaxTemp,
    )
}
