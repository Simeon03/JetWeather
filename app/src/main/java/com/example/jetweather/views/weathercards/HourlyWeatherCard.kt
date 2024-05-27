package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.HourlyWeatherRows

@Composable
fun HourlyWeatherCard(viewModel: HourlyWeatherViewModel) {
    WeatherCard {
        HourlyWeatherRows(viewModel = viewModel)
    }
}
