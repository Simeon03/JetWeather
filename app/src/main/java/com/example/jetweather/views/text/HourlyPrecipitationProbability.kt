package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.DefaultWeatherTypography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun HourlyPrecipitationProbability(text: String) {
    Text(
        text = text,
        style = DefaultWeatherTypography.small,
        textAlign = TextAlign.Center,
        color = primaryP10
    )
}
