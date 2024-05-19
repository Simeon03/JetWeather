package com.example.jetweather.views.hourlyweather.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyWeatherHour(text: String) {
    Text(
        text = text,
        style = Typography.titleSmall,
        color = primaryP10
    )
}