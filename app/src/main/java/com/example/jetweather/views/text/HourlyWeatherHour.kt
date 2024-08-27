package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun HourlyWeatherHour(text: String) {
    Text(
        text = text,
        style = Typography.bodyMedium,
        fontWeight = Typography.titleSmall.fontWeight,
        color = primaryP10
    )
}