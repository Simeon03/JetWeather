package com.example.jetweather.views.currentweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.currentweather.layouts.CurrentWeatherInfo

@Composable
fun CurrentWeatherView(viewModel: CurrentWeatherViewModel) {
    val viewModel by viewModel.currentWeatherData.collectAsState()

    CurrentWeatherInfo(
        currentLocation = viewModel.location,
        currentTemp = viewModel.temp.toString(),
        currentApparentTemp = viewModel.apparentTemp.roundTemp(),
        currentWeatherStatus = viewModel.weatherStatus?.formatWeatherCodeToText() ?: "",
        currentMinTemp = viewModel.minTemp.roundTemp(),
        currentMaxTemp = viewModel.maxTemp.roundTemp(),
    )
}
