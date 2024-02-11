package com.example.jetweather.views.hourlyweather.subviews

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helper.DataFormatter.weatherIcon

@Composable
fun HourlyWeatherIconView(
    weatherCode: Int,
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = weatherIcon(weatherCode)),
        contentDescription = formatWeatherCodeToText(weatherCode),
        tint = Color.Unspecified,
        modifier = modifier.size(28.dp)
    )
}