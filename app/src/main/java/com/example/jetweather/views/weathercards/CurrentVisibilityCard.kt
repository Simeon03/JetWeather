package com.example.jetweather.views.weathercards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.jetweather.helpers.DataFormatter.getVisibilityStatus
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.views.WeatherCard
import com.example.jetweather.views.layout.CurrentVisibilityInfo

@Composable
fun CurrentVisibilityCard(viewModel: CurrentHourWeatherViewModel, modifier: Modifier = Modifier) {
    val currentHourWeather by viewModel.currentHourWeatherData.collectAsState()

    WeatherCard(modifier = modifier) {
        CurrentVisibilityInfo(visibility = currentHourWeather.visibility.getVisibilityStatus())
    }
}
