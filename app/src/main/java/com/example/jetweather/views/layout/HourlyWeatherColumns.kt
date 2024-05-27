package com.example.jetweather.views.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.roundPercent
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.HourlyWeatherViewModel

@Composable
fun HourlyWeatherColumns(
    viewModel: HourlyWeatherViewModel,
    index: Int
) {
    val viewModel by viewModel.hourlyWeatherData.collectAsState()

    HourlyWeatherColumn(
        hours = viewModel.time[index],
        temps = viewModel.temperature.map { it.roundTemp() }[index],
        weatherStatus = viewModel.weatherStatus[index],
        relativeHumidity = viewModel.humidity.map { it.roundPercent() }[index]
    )
}
