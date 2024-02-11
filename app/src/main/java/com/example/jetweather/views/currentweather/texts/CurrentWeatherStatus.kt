package com.example.jetweather.views.currentweather.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentWeatherStatus(text: String) {
    Text(
        text = text,
        style = Typography.bodyLarge,
    )
}