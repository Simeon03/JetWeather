package com.example.jetweather.helper.views.customprogressbar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.indicator(
    pos: Float,
    radius: Float,
    size: Size
) {
    val indicatorColor = Color.White
    val indicatorRadius = radius / 2
    val indicatorPosX = pos * 1
    val indicatorPosY = size.height / 2
    val indicatorPos = Offset(x = indicatorPosX, y = indicatorPosY)

    drawCircle(
        color = indicatorColor,
        radius = indicatorRadius,
        center = indicatorPos
    )
}