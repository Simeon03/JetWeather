package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherIconView
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherSlotHourView
import com.example.jetweather.views.hourlyweather.subviews.HourlyWeatherSlotTemperatureView

@Composable
fun HourlyWeatherSlotView(
    time: String,
    hourlyTemperature: String,
    weatherCode: Int,
    weatherCodeDesc: String,
    modifier: Modifier = Modifier,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        HourlyWeatherSlotHourView(text = time)
        HourlyWeatherIconView(weatherCode = weatherCode, weatherCodeDesc = weatherCodeDesc, modifier = modifier)
        HourlyWeatherSlotTemperatureView(text = hourlyTemperature)
    }
}
