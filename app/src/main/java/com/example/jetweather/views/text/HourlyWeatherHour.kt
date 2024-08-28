package com.example.jetweather.views.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyWeatherHour(text: String) {
    Text(
        text = text,
        style = Typography.labelMedium,
        color = MaterialTheme.colorScheme.primary
    )
}