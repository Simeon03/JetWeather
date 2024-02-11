package com.example.jetweather.helper.views.customprogressbar

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(sunrise: Float, sunset: Float, currentTime: Float, barHeight: Dp, gradientColor: List<Color>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val cornerRadius = 100.dp
        val outerRectangleSize = size
        val innerRectangleWidth = outerRectangleSize.width * (sunset - sunrise)
        val innerRectangleOffset = outerRectangleSize.width * sunrise
        val circleCenter = outerRectangleSize.width * currentTime
        val circleRadius = barHeight.toPx()

        fullBar(outerRectangleSize, cornerRadius)
        subBar(innerRectangleWidth, innerRectangleOffset, outerRectangleSize, cornerRadius, gradientColor)
        indicator(circleCenter, circleRadius, outerRectangleSize)
    }
}
