package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun CurrentWeatherView(viewModel: WeatherViewModel) {
    // State for current weather data
    val currentWeatherStatus by viewModel.currentWeatherStatusText.collectAsState()
    val currentTemp by viewModel.currentTempText.collectAsState()
    val currentMinTemp by viewModel.currentMinTempText.collectAsState()
    val currentMaxTemp by viewModel.currentMaxTempText.collectAsState()

    val currentMinMaxTemp = "$currentMinTemp°/$currentMaxTemp°"

    // UI layout
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurrentTemperature(text = currentTemp)
        CurrentWeatherCode(text = currentWeatherStatus)
        CurrentWeatherCode(text = currentMinMaxTemp)
    }
}
