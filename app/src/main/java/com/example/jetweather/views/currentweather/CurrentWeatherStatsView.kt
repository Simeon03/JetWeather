package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.currentweather.subviews.CurrentLocationView
import com.example.jetweather.views.currentweather.subviews.CurrentMinMaxTempView
import com.example.jetweather.views.currentweather.subviews.CurrentTemperatureView
import com.example.jetweather.views.currentweather.subviews.CurrentWeatherStatusView

@Composable
fun CurrentWeatherStatsView(
    currentLocation: String,
    currentTemp: String,
    currentWeatherStatus: String,
    currentMinTemp: String,
    currentMaxTemp: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CurrentLocationView(text = currentLocation)
        CurrentTemperatureView(text = currentTemp)
        CurrentWeatherStatusView(text = currentWeatherStatus)
        CurrentMinMaxTempView(text = "$currentMinTemp/$currentMaxTemp")
    }
}