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
    val gradientColor = listOf(SunGradient1, SunGradient2, SunGradient3)
    val innerGradient = Brush.horizontalGradient(colors = gradientColor)
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