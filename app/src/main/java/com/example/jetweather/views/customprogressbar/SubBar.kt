package com.example.jetweather.views.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.subBar(
    width: Float,
    offset: Float,
    size: Size,
    cornerRadius: Dp,
    gradientColors: List<Color>,
) {
    val gradient = Brush.horizontalGradient(colors = gradientColors)
    val width = width
    val height = size.height
    val size = Size(width = width, height = height)
    val offset = Offset(x = offset, y = 0f)
    val cornerRadius = cornerRadius.toPx()

    drawRoundRect(
        brush = gradient,
        topLeft = offset,
        size = size,
        cornerRadius = CornerRadius(cornerRadius)
    )
}