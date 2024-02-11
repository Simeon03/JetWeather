package com.example.jetweather.views.dailyweather.subviews

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.jetweather.helper.DataFormatter.formatWeatherCodeText
import com.example.jetweather.helper.DataFormatter.weatherIcon

@Composable
fun DailyWeatherIconView(
    weatherCode: Int,
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = weatherIcon(weatherCode)),
        contentDescription = formatWeatherCodeText(weatherCode),
        tint = Color.Unspecified,
        modifier = modifier
    )
}