package com.example.jetweather.views.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetweather.helpers.views.customprogressbar.CustomProgressBar
import com.example.jetweather.helpers.views.customprogressbar.ProgressBarContainer
import com.example.jetweather.ui.theme.primaryP60

@Composable
fun DaylightProgressBar(
    sunrisePercentage: Float,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    val barHeight = 16.dp
    val sunlightGradientColors = listOf(primaryP60, primaryP60)

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
