package com.example.jetweather.helper.views.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.subBar(
    innerRectangleWidth: Float,
    innerRectangleOffset: Float,
    outerRectangleSize: Size,
    cornerRadius: Dp,
    gradientColors: List<Color>,
) {
    val innerGradient = Brush.horizontalGradient(colors = gradientColors)
    val width = innerRectangleWidth
    val height = outerRectangleSize.height
    val size = Size(width = width, height = height)
    val offset = Offset(x = innerRectangleOffset, y = 0f)
    val cornerRadius = cornerRadius.toPx()

    drawRoundRect(
        brush = innerGradient,
        topLeft = offset,
        size = size,
        cornerRadius = CornerRadius(cornerRadius)
    )
}