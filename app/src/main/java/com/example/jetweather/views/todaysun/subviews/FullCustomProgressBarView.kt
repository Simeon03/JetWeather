package com.example.jetweather.views.todaysun.subviews

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.todaysun.subviews.customprogressbar.ProgressBarCanvas
import com.example.jetweather.views.todaysun.subviews.customprogressbar.ProgressBarContainer

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
