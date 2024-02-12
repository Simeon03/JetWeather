package com.example.jetweather.views.hourlyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.MainViewModel

@Composable
fun HourlyWeatherInfo(
    viewModel: MainViewModel,
    index: Int
) {
    val weatherDataText by viewModel.weatherDataText.collectAsState()

    HourlyWeather(
        hours = weatherDataText.hourlyTime[index],
        temps = weatherDataText.hourlyTemperature[index],
        weatherStatus = weatherDataText.hourlyWeatherStatus[index],
        relativeHumidity = weatherDataText.hourlyHumidity[index]
    )
}
