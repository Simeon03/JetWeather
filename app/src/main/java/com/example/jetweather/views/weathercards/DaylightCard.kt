package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetweather.helpers.DataFormatter.fetchTime
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.component.Daylight
import com.example.jetweather.views.weathercards.layout.WeatherCard

@Composable
fun DaylightCard() {
    val viewModel: CurrentWeatherViewModel = hiltViewModel()
    val current by viewModel.currentWeatherData.collectAsState()

    WeatherCard {
        Daylight(
            sunriseTime = current.sunriseTime.fetchTime(),
            sunrisePercentage = current.sunriseTime.getPercentageOfDay(),
            sunsetTime = current.sunsetTime.fetchTime(),
            sunsetPercentage = current.sunsetTime.getPercentageOfDay(),
            currentTimePercentage = getCurrentTimePercentage()
        )
    }
}
