package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.DailyWeatherInfo

@Composable
fun DailyWeatherCard(viewModel: WeeklyWeatherViewModel) {
    val viewModel by viewModel.weeklyWeatherData.collectAsState()

    WeatherCard {
        DailyWeatherInfo(
            minTemp = viewModel.minTemp,
            maxTemp = viewModel.maxTemp,
            date = viewModel.day,
            weatherCode = viewModel.weatherStatus,
        )
    }
}