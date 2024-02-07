package com.example.jetweather.views.hourlyweather

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyWeatherSlotTemperatureView(temperature: String) {
    Text(
        text = temperature,
        style = Typography.titleSmall,
        textAlign = TextAlign.Center,
    )
}