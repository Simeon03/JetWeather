package com.example.jetweather.views.dailyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter
import com.example.jetweather.helpers.DataFormatter.formatDay
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel

@Composable
fun DailyWeatherInfo(index: Int, viewModel: WeeklyWeatherViewModel) {
    val viewModel by viewModel.weeklyWeatherData.collectAsState()

    DailyWeather(
        minTemp = viewModel.weeklyMinTemp.map { formatTemperatureText(it) }[index],
        maxTemp = viewModel.weeklyMaxTemp.map { formatTemperatureText(it) }[index],
        date = viewModel.weeklyDay.map { formatDay(it) }[index],
        weatherCode = viewModel.weeklyWeatherStatus.map { DataFormatter.formatWeatherCodeToIcon(it) }[index],
    )

}