package com.example.jetweather.views.hourlyweather.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyWeatherSlotHourView(text: String) {
    Text(
        text = text,
        style = Typography.titleSmall
    )
}