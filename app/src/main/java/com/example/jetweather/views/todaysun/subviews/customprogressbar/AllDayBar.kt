package com.example.jetweather.views.todaysun.subviews.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.allDayBar(outerRectangleSize: Size, cornerRadius: Dp) {
    val gradientColors = listOf(Color.DarkGray.copy(alpha = 0.9f), Color.DarkGray.copy(alpha = 0.9f))

    val outerGradient = Brush.horizontalGradient(colors = gradientColors)

    drawRoundRect(
        brush = outerGradient,
        size = outerRectangleSize,
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}
