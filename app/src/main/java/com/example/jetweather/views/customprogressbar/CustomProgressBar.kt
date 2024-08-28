package com.example.jetweather.views.customprogressbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(
    subBarStart: Float,
    subBarEnd: Float,
    indicatorPos: Float,
    barHeight: Dp,
    subBarGradientColor: List<Color>,
    indicatorColor: Color,
    fullBarColor: Color,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val barsCornerRadius = 100.dp
        val fullBarSize = size
        val subBarWidth = fullBarSize.width * (subBarEnd - subBarStart)
        val subBarOffset = fullBarSize.width * subBarStart
        val indicatorPos = fullBarSize.width * indicatorPos
        val indicatorRadius = barHeight.toPx()

        fullBar(
            barSize = fullBarSize,
            cornerRadius = barsCornerRadius,
            fullBarColor = fullBarColor,
        )

        subBar(
            width = subBarWidth,
            offset = subBarOffset,
            size = fullBarSize,
            cornerRadius = barsCornerRadius,
            gradientColors = subBarGradientColor,
        )

        indicator(
            indicatorPos,
            indicatorRadius,
            fullBarSize,
            indicatorColor,
        )
    }
}
