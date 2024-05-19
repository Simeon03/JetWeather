package com.example.jetweather.helpers.views.customprogressbar

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import com.example.jetweather.ui.theme.primaryP70

fun DrawScope.fullBar(
    barSize: Size,
    cornerRadius: Dp
) {
    val gradientColors = listOf(primaryP70, primaryP70)
    val outerGradient = Brush.horizontalGradient(colors = gradientColors)

    drawRoundRect(
        brush = outerGradient,
        size = barSize,
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}
