package com.example.jetweather.views.currentweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.views.currentweather.subviews.CurrentMinMaxTemp
import com.example.jetweather.views.currentweather.subviews.CurrentTemperature
import com.example.jetweather.views.currentweather.subviews.CurrentWeatherCode
import com.example.jetweather.views.currentweather.subviews.LocationView

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
    ) {
        LocationView(text = currentLocation)
        CurrentTemperature(text = currentTemp)
        CurrentWeatherCode(text = currentWeatherStatus)
        CurrentMinMaxTemp(text = "$currentMinTemp/$currentMaxTemp")
    }
}