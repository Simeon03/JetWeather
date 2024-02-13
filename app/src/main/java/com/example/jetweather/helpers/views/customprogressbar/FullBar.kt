package com.example.jetweather.helpers.views.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.fullBar(
    barSize: Size,
    cornerRadius: Dp
) {
    val gradientColors = listOf(Color.White.copy(alpha = 0.2f), Color.White.copy(alpha = 0f))
    val outerGradient = Brush.horizontalGradient(colors = gradientColors)

    drawRoundRect(
        brush = outerGradient,
        size = barSize,
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}
