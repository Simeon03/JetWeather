package com.example.jetweather.views.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.fetchDay
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToIcon
import com.example.jetweather.helpers.DataFormatter.roundTemp
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel

@Composable
fun DailyWeatherInfo(index: Int, viewModel: WeeklyWeatherViewModel) {
    val viewModel by viewModel.weeklyWeatherData.collectAsState()

    DailyWeatherRow(
        minTemp = viewModel.minTemp.map { it.roundTemp() }[index],
        maxTemp = viewModel.maxTemp.map { it.roundTemp() }[index],
        date = viewModel.day.map { it.fetchDay() }[index],
        weatherCode = viewModel.weatherStatus.map { it.formatWeatherCodeToIcon() }[index],
    )
}