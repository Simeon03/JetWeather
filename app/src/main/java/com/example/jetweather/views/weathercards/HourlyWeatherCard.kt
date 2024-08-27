package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.HourlyWeatherInfo

@Composable
fun HourlyWeatherCard(viewModel: HourlyWeatherViewModel) {
    val viewModel by viewModel.hourlyWeatherData.collectAsState()

    WeatherCard {
        HourlyWeatherInfo(
            hours = viewModel.time,
            temps = viewModel.temperature,
            weatherStatus = viewModel.weatherStatus,
            precipitationProbability = viewModel.hourlyPrecipitationProbability
        )
    }
}
