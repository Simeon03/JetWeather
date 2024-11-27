package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.info.DailyWeatherInfo
import com.example.jetweather.views.weathercards.layout.WeatherCard

@Composable
fun DailyWeatherCard() {
    val viewModel: WeeklyWeatherViewModel = hiltViewModel()
    val daily by viewModel.weeklyWeatherData.collectAsState()

    WeatherCard {
        DailyWeatherInfo(
            minTemp = daily.minTemp,
            maxTemp = daily.maxTemp,
            date = daily.day,
            weatherCode = daily.weatherStatus,
        )
    }
}