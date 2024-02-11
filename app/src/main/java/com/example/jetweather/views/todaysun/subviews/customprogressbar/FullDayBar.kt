package com.example.jetweather.views.todaysun.subviews.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.fullDayBar(outerRectangleSize: Size, cornerRadius: Dp) {
    // Define gradient for the outer rectangle
    val outerGradient = Brush.horizontalGradient(
        colors = listOf(Color.DarkGray.copy(alpha = 0.9f), Color.DarkGray.copy(alpha = 0.9f))
    )
    // Draw outer rectangle with gradient
    drawRoundRect(
        brush = outerGradient,
        size = outerRectangleSize,
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}
