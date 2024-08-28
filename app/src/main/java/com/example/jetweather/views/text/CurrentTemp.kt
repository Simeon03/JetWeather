package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetweather.ui.theme.DefaultWeatherTypography

@Composable
fun CurrentTemp(text: String) {
    Text(
        text = text,
        style = DefaultWeatherTypography.extraLarge,
    )
}