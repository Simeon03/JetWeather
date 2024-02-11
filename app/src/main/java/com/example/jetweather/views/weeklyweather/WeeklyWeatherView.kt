package com.example.jetweather.views.weeklyweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun WeeklyWeatherView(viewModel: WeatherViewModel) {
    val weatherDataLoading by viewModel.isLoading.collectAsState()

    if (!weatherDataLoading) { WeatherCardContent(viewModel) }
}
