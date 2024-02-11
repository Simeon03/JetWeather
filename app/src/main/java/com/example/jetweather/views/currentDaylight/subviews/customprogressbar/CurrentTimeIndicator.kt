package com.example.jetweather.views.currentDaylight.subviews.customprogressbar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.currentTimeIndicator(circleCenter: Float, circleRadius: Float, outerRectangleSize: Size) {
    val indicatorColor = Color.White
    val indicatorRadius = circleRadius / 2
    val indicatorCenterX = circleCenter
    val indicatorCenterY = outerRectangleSize.height / 2
    val indicatorCenter = Offset(x = indicatorCenterX, y = indicatorCenterY)

    drawCircle(
        color = indicatorColor,
        radius = indicatorRadius,
        center = indicatorCenter
    )
}