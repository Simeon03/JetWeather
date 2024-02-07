package com.example.jetweather.views.weeklyweather

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography

@Composable
fun DayOfWeatherView(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Start,
        style = Typography.titleSmall,
    )
}