package com.example.jetweather.helpers.views.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.fullBar(
    barSize: Size,
    cornerRadius: Dp,
    fullBarColor: Color,
) {
    val gradientColors = listOf(fullBarColor, fullBarColor)
    val outerGradient = Brush.horizontalGradient(colors = gradientColors)

    drawRoundRect(
        brush = outerGradient,
        size = barSize,
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}
