package com.example.jetweather.views.todaysun.subviews.customprogressbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBarCanvas(sunrise: Float, sunset: Float, currentTime: Float, barHeight: Dp) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val cornerRadius = 100.dp
        val outerRectangleSize = size
        val innerRectangleWidth = outerRectangleSize.width * (sunset - sunrise)
        val innerRectangleOffset = outerRectangleSize.width * sunrise
        val circleCenter = outerRectangleSize.width * currentTime
        val circleRadius = barHeight.toPx()

        allDayBar(outerRectangleSize, cornerRadius)
        sunsetSunriseBar(innerRectangleWidth, innerRectangleOffset, outerRectangleSize, cornerRadius)
        currentTimeIndicator(circleCenter, circleRadius, outerRectangleSize)
    }
}
