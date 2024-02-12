package com.example.jetweather.views.dailyweather.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.MainViewModel

@Composable
fun DailyWeatherInfo(viewModel: MainViewModel, index: Int) {
    val weatherDataText by viewModel.weatherDataText.collectAsState()

    DailyWeather(
        minTemp = weatherDataText.weeklyMinTemp[index],
        maxTemp = weatherDataText.weeklyMaxTemp[index],
        date = weatherDataText.weeklyDay[index],
        weatherCode = weatherDataText.weeklyWeatherStatus[index],
    )
}