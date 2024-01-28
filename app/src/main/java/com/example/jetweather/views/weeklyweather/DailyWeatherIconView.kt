package com.example.jetweather.views.weeklyweather

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.jetweather.helper.weatherCodeIcon

@Composable
fun DailyWeatherIconView(weatherCode: Int, weatherCodeDesc: String, modifier: Modifier) {
    Icon(
        painter = painterResource(id = weatherCodeIcon(weatherCode)),
        contentDescription = weatherCodeDesc,
        tint = Color.Unspecified,
        modifier = modifier
    )
}