package com.example.jetweather.views.todaysun.subviews.customprogressbar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.currentTimeIndicator(circleCenter: Float, circleRadius: Float, outerRectangleSize: Size) {
    // Draw the circle for the current time
    drawCircle(
        color = Color.White,
        radius = circleRadius / 2,
        center = Offset(x = circleCenter, y = outerRectangleSize.height / 2)
    )
}