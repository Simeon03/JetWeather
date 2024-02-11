package com.example.jetweather.views.todaysun.subviews.customprogressbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(
    sunrisePercentage: Float,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    val barHeight = 20.dp
    ProgressBarContainer(barHeight = barHeight) {
        ProgressBarCanvas(sunrisePercentage, sunsetPercentage, currentTimePercentage, barHeight)
    }
}
