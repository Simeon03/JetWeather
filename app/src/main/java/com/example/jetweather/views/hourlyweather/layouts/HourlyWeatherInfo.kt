package com.example.jetweather.views.hourlyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.HourlyWeatherViewModel

@Composable
fun HourlyWeatherInfo(
    viewModel: HourlyWeatherViewModel,
    index: Int
) {
    val viewModel by viewModel.hourlyWeatherData.collectAsState()

    HourlyWeather(
        hours = viewModel.time[index],
        temps = viewModel.temperature.map { it.roundTemp() }[index],
        weatherStatus = viewModel.weatherStatus[index],
        relativeHumidity = viewModel.humidity.map { formatRelativeHumidityText(it) }[index]
    )
}
