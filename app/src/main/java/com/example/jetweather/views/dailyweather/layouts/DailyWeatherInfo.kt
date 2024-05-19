package com.example.jetweather.views.dailyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter
import com.example.jetweather.helpers.DataFormatter.formatDay
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel

@Composable
fun DailyWeatherInfo(index: Int, viewModel: WeeklyWeatherViewModel) {
    val viewModel by viewModel.weeklyWeatherData.collectAsState()

    DailyWeather(
        minTemp = viewModel.minTemp.map { it.roundTemp() }[index],
        maxTemp = viewModel.maxTemp.map { it.roundTemp() }[index],
        date = viewModel.day.map { formatDay(it) }[index],
        weatherCode = viewModel.weatherStatus.map { DataFormatter.formatWeatherCodeToIcon(it) }[index],
    )

}