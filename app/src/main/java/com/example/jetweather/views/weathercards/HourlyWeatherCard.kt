package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.views.info.HourlyWeatherInfo
import com.example.jetweather.views.weathercards.layout.WeatherCard

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
