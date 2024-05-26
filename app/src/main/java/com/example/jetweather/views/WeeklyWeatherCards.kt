package com.example.jetweather.views

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.dailyweather.DailyWeatherView

@Composable
fun WeeklyWeatherCards(weekly: WeeklyWeatherViewModel) {
    DailyWeatherView(viewModel = weekly)
}
