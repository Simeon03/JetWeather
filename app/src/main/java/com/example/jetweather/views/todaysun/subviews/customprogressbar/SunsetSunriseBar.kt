package com.example.jetweather.views.todaysun.subviews.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import com.example.jetweather.ui.theme.SunGradient1
import com.example.jetweather.ui.theme.SunGradient2
import com.example.jetweather.ui.theme.SunGradient3

fun DrawScope.sunsetSunriseBar(innerRectangleWidth: Float, innerRectangleOffset: Float, outerRectangleSize: Size, cornerRadius: Dp) {
    // Define gradient for the inner rectangle
    val innerGradient = Brush.horizontalGradient(
        colors = listOf(SunGradient1, SunGradient2, SunGradient3)
    )
    // Draw inner rectangle with gradient
    drawRoundRect(
        brush = innerGradient,
        topLeft = Offset(x = innerRectangleOffset, y = 0f),
        size = Size(width = innerRectangleWidth, height = outerRectangleSize.height),
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}