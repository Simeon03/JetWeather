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
fun HourlyWeatherLayoutView(
    hours: String,
    temps: String,
    weatherStatus: Int,
    weatherStatusDesc: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HourlyWeatherSlotHourView(text = hours)
        HourlyWeatherIconView(weatherCode = weatherStatus, weatherCodeDesc = weatherStatusDesc, modifier = modifier)
        HourlyWeatherSlotTemperatureView(text = temps)
    }
}