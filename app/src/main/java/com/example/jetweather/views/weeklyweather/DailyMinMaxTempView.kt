package com.example.jetweather.views.weeklyweather

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography

@Composable
fun DailyMinMaxTempView(minTemp: Int, maxTemp: Int, modifier: Modifier = Modifier) {
    Text(
        text = "$maxTemp°/$minTemp°",
        modifier = modifier,
        textAlign = TextAlign.End,
        style = Typography.titleSmall
    )
}