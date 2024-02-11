package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.views.customprogressbar.CustomProgressBar
import com.example.jetweather.helper.views.customprogressbar.ProgressBarContainer
import com.example.jetweather.ui.theme.SunGradient1
import com.example.jetweather.ui.theme.SunGradient2
import com.example.jetweather.ui.theme.SunGradient3

@Composable
fun FullDaylightProgressBarView(
    sunrisePercentage: Float,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    val barHeight = 16.dp
    val gradientColor = listOf(SunGradient1, SunGradient2, SunGradient3)

    ProgressBarContainer(barHeight = barHeight) {
        CustomProgressBar(sunrisePercentage, sunsetPercentage, currentTimePercentage, barHeight, gradientColor)
    }
}
