package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherSlotHourView
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherSlotTemperatureView

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
