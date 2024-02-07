package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun HourlyWeatherSlotView(
    time: String,
    hourlyTemperature: String,
) {
    Column {
        HourlyWeatherSlotHourView(text = time)
        HourlyWeatherSlotTemperatureView(text = hourlyTemperature)
    }
}
