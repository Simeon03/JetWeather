package com.example.jetweather.views.hourlyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.viewmodel.MainViewModel

@Composable
fun HourlyWeatherInfo(
    viewModel: MainViewModel,
    index: Int
) {
    val weatherData by viewModel.weatherData.collectAsState()

    val hours = weatherData.hourlyTime[index]
    val temps = formatTemperatureText(weatherData.hourlyTemperature[index])
    val weatherStatus = weatherData.hourlyWeatherStatus[index]
    val relativeHumidity = formatRelativeHumidityText(weatherData.hourlyHumidity[index])

    HourlyWeather(
        hours = hours,
        temps = temps,
        weatherStatus = weatherStatus,
        relativeHumidity = relativeHumidity
    )
}
