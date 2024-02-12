package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.MainViewModel
import com.example.jetweather.views.currentweather.layouts.CurrentWeatherInfo

@Composable
fun CurrentWeatherView(viewModel: MainViewModel) {
    val weatherDataText by viewModel.weatherDataText.collectAsState()

    CurrentWeatherInfo(
        currentLocation = weatherDataText.currentLocation,
        currentTemp = weatherDataText.currentTemp,
        currentWeatherStatus = weatherDataText.currentWeatherStatus,
        currentMinTemp = weatherDataText.currentMinTemp,
        currentMaxTemp = weatherDataText.currentMaxTemp,
    )
}
