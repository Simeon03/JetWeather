package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.currentweather.layouts.CurrentWeatherInfo

@Composable
fun CurrentWeatherView(viewModel: CurrentWeatherViewModel) {
    val viewModel by viewModel.currentWeatherData.collectAsState()

    CurrentWeatherInfo(
        currentLocation = viewModel.currentLocation,
        currentTemp = viewModel.currentTemp.toString(),
        currentApparentTemp = formatTemperatureText(viewModel.currentApparentTemp),
        currentWeatherStatus = formatWeatherCodeToText(viewModel.currentWeatherStatus ?: 0),
        currentMinTemp = formatTemperatureText(viewModel.currentMinTemp),
        currentMaxTemp = formatTemperatureText(viewModel.currentMaxTemp),
    )
}
