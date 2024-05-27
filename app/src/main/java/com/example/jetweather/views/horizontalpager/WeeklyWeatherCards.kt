package com.example.jetweather.views.horizontalpager

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.weathercards.DailyWeatherCard

@Composable
fun WeeklyWeatherCards(weekly: WeeklyWeatherViewModel) {
    DailyWeatherCard(viewModel = weekly)
}
