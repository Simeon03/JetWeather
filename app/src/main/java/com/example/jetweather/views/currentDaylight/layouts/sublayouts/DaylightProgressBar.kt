package com.example.jetweather.views.currentDaylight.layouts.sublayouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetweather.helpers.views.customprogressbar.CustomProgressBar
import com.example.jetweather.helpers.views.customprogressbar.ProgressBarContainer
import com.example.jetweather.ui.theme.SunGradient1
import com.example.jetweather.ui.theme.SunGradient2
import com.example.jetweather.ui.theme.SunGradient3

@Composable
fun DaylightProgressBar(
    sunrisePercentage: Float,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    val barHeight = 16.dp
    val sunlightGradientColors = listOf(SunGradient1, SunGradient2, SunGradient3)

    ProgressBarContainer(barHeight = barHeight) {
        CustomProgressBar(
            subBarStart = sunrisePercentage,
            subBarEnd = sunsetPercentage,
            indicatorPos = currentTimePercentage,
            barHeight = barHeight,
            subBarGradientColor = sunlightGradientColors
        )
    }
}
