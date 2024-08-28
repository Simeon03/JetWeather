package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.DefaultWeatherTypography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun HourlyWeatherHour(text: String) {
    Text(
        text = text,
        style = DefaultWeatherTypography.smallBold,
        color = primaryP10
    )
}