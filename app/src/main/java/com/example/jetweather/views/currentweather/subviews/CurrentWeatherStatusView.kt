package com.example.jetweather.views.currentweather.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentWeatherStatusView(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
    )
}