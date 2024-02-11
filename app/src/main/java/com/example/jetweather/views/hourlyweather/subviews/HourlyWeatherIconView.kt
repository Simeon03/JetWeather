package com.example.jetweather.views.hourlyweather.subviews

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.DataFormatter

@Composable
fun HourlyWeatherIconView(weatherCode: Int, weatherCodeDesc: String, modifier: Modifier) {
    Icon(
        painter = painterResource(id = DataFormatter.weatherIcon(weatherCode)),
        contentDescription = weatherCodeDesc,
        tint = Color.Unspecified,
        modifier = modifier.size(28.dp)
    )
}