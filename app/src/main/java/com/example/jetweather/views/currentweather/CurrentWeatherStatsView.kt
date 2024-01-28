package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CurrentWeatherStatsView(
    currentTemp: String,
    currentWeatherStatus: String,
    currentMinTemp: Int,
    currentMaxTemp: Int,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurrentTemperature(text = currentTemp)
        CurrentWeatherCode(text = currentWeatherStatus)
        CurrentMinMaxTemp(text = "$currentMinTemp°/$currentMaxTemp°")
    }
}