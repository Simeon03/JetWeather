package com.example.jetweather.views.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.DefaultWeatherTypography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun DailyMinMaxTemp(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = DefaultWeatherTypography.normal,
        textAlign = TextAlign.End,
        color = primaryP10
    )
}