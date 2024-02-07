package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CurrentWeatherStatsView(
    currentLocation: String,
    currentTemp: String,
    currentWeatherStatus: String?,
    currentMinTemp: String,
    currentMaxTemp: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LocationView(location = currentLocation)
        CurrentTemperature(text = currentTemp)
        CurrentWeatherCode(text = currentWeatherStatus ?: "N/A")
        CurrentMinMaxTemp(text = "$currentMinTemp/$currentMaxTemp")
    }
}