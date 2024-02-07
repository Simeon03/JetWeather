package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun HourlyWeatherSlotView(
    time: String,
    hourlyTemperature: String,
) {
    Column {
        HourlyWeatherSlotHourView(time = time)
        HourlyWeatherSlotTemperatureView(temperature = hourlyTemperature)
    }
}
