package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.fetchTime
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.Daylight

@Composable
fun DaylightCard(viewModel: CurrentWeatherViewModel) {
    val viewModel by viewModel.currentWeatherData.collectAsState()

    WeatherCard {
        Daylight(
            sunriseTime = viewModel.sunriseTime.fetchTime(),
            sunrisePercentage = viewModel.sunriseTime.getPercentageOfDay(),
            sunsetTime = viewModel.sunsetTime.fetchTime(),
            sunsetPercentage = viewModel.sunsetTime.getPercentageOfDay(),
            currentTimePercentage = getCurrentTimePercentage()
        )
    }
}
