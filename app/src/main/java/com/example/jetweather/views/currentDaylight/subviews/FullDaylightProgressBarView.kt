package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.currentDaylight.subviews.customprogressbar.ProgressBarCanvas
import com.example.jetweather.views.currentDaylight.subviews.customprogressbar.ProgressBarContainer

@Composable
fun FullDaylightProgressBarView(
    sunrisePercentage: Float,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    val barHeight = 16.dp
    ProgressBarContainer(barHeight = barHeight) {
        ProgressBarCanvas(sunrisePercentage, sunsetPercentage, currentTimePercentage, barHeight)
    }
}
