package com.example.jetweather.views.dailyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter.formatDay
import com.example.jetweather.helper.DataFormatter.formatTemperatureText
import com.example.jetweather.helper.DataFormatter.formatWeatherCodeToIcon
import com.example.jetweather.viewmodel.MainViewModel

@Composable
fun DailyWeatherInfo(viewModel: MainViewModel, index: Int) {
    val weatherData by viewModel.weatherData.collectAsState()

    val weeklyMinTemp = formatTemperatureText(weatherData.weeklyMinTemp[index])
    val weeklyMaxTemp = formatTemperatureText(weatherData.weeklyMaxTemp[index])
    val dayOfWeek = formatDay(weatherData.weeklyDay.getOrElse(index) { "2023-02-01" })
    val weeklyWeatherCode = formatWeatherCodeToIcon(weatherData.weeklyWeatherStatus[index])

    DailyWeather(
        minTemp = weeklyMinTemp,
        maxTemp = weeklyMaxTemp,
        date = dayOfWeek,
        weatherCode = weeklyWeatherCode,
    )
}