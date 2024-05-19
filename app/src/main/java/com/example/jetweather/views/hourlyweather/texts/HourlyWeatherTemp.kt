package com.example.jetweather.views.hourlyweather.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.ui.theme.Typography

@Composable
fun HourlyWeatherTemp(text: String) {
    Text(
        text = text,
        style = Typography.titleSmall,
        textAlign = TextAlign.Center,
        color = primaryP10,
    )
}