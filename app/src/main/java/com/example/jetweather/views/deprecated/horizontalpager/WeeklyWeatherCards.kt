package com.example.jetweather.views.deprecated.horizontalpager

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.weathercards.DailyWeatherCard

@Composable
fun WeeklyWeatherCards(viewModel: WeeklyWeatherViewModel) {
    DailyWeatherCard(viewModel = viewModel)
}
