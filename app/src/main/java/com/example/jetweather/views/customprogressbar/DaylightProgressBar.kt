package com.example.jetweather.views.customprogressbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun DaylightProgressBar(
    sunrisePercentage: Float,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    val barHeight = 16.dp
    val sunlightGradientColors = listOf(MaterialTheme.colorScheme.onSecondaryContainer, MaterialTheme.colorScheme.onSecondaryContainer)

    ProgressBarContainer(barHeight = barHeight) {
        CustomProgressBar(
            subBarStart = sunrisePercentage,
            subBarEnd = sunsetPercentage,
            indicatorPos = currentTimePercentage,
            barHeight = barHeight,
            subBarGradientColor = sunlightGradientColors,
            indicatorColor = MaterialTheme.colorScheme.primary,
            fullBarColor = MaterialTheme.colorScheme.tertiary
        )
    }
}
