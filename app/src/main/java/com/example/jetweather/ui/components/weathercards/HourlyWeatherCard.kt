package com.example.jetweather.ui.components.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.ui.components.info.HourlyWeatherInfo
import com.example.jetweather.ui.components.weathercards.layout.WeatherCard

@Composable
fun HourlyWeatherCard() {
    val viewModel: HourlyWeatherViewModel = hiltViewModel()
    val hourly by viewModel.hourlyWeatherData.collectAsState()

    WeatherCard {
        HourlyWeatherInfo(
            hours = hourly.time,
            temps = hourly.temperature,
            weatherStatus = hourly.weatherStatus,
            precipitationProbability = hourly.hourlyPrecipitationProbability
        )
    }
}
