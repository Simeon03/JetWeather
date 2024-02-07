package com.example.jetweather.views.hourlyweather

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyWeatherSlotHourView(time: String) {
    Text(
        text = time,
        style = Typography.titleSmall
    )
}