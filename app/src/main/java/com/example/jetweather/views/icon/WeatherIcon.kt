package com.example.jetweather.views.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helpers.DataFormatter.getWeatherIcon

@Composable
fun WeatherIcon(
    weatherCode: Int,
    size: Dp,
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = weatherCode.getWeatherIcon()),
        contentDescription = weatherCode.formatWeatherCodeToText(),
        tint = Color.Unspecified,
        modifier = modifier.size(size)
    )
}