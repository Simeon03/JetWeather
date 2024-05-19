package com.example.jetweather.views.hourlyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter
import com.example.jetweather.helpers.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.viewmodel.HourlyWeatherViewModel

@Composable
fun HourlyWeatherInfo(
    viewModel: HourlyWeatherViewModel,
    index: Int
) {
    val viewModel by viewModel.hourlyWeatherData.collectAsState()

    HourlyWeather(
        hours = viewModel.hourlyTime[index],
        temps = viewModel.hourlyTemperature.map { DataFormatter.formatTemperatureText(it) }[index],
        weatherStatus = viewModel.hourlyWeatherStatus[index],
        relativeHumidity = viewModel.hourlyHumidity.map { formatRelativeHumidityText(it) }[index]
    )
}
