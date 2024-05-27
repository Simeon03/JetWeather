package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.DailyWeatherColumn

@Composable
fun DailyWeatherCard(viewModel: WeeklyWeatherViewModel) {
    WeatherCard {
        DailyWeatherColumn(viewModel = viewModel)
    }
}