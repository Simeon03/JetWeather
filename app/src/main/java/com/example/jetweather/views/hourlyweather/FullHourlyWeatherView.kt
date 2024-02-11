package com.example.jetweather.views.hourlyweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.helper.DataFormatter.formatTemperatureText
import com.example.jetweather.helper.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun FullHourlyWeatherView(
    viewModel: WeatherViewModel,
    index: Int
) {
    val weatherData by viewModel.weatherData.collectAsState()

    val hours = weatherData.hourlyTime[index]
    val temps = formatTemperatureText(weatherData.hourlyTemperature[index])
    val weatherStatus = weatherData.hourlyWeatherStatus[index]
    val weatherStatusDesc = formatWeatherCodeToText(weatherStatus)
    val relativeHumidity = formatRelativeHumidityText(weatherData.hourlyHumidity[index])

    HourlyWeatherLayoutView(
        hours = hours,
        temps = temps,
        weatherStatus = weatherStatus,
        relativeHumidity = relativeHumidity,
        weatherStatusDesc = weatherStatusDesc
    )
}
