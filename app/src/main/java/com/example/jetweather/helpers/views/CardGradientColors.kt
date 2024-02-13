package com.example.jetweather.helpers.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun cardGradientColors(): Brush {
    val gradientColors = listOf(
        Color.Black.copy(alpha = 0.4f),
        Color.Black.copy(alpha = 0.3f)
    )
    return Brush.linearGradient(gradientColors)
}
