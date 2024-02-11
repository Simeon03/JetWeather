package com.example.jetweather.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2

@Composable
fun gradientBackground(): Brush {
    val gradientColors = listOf(
        Gradient1.copy(alpha = 1f),
        Gradient2.copy(alpha = 1f)
    )
    return Brush.verticalGradient(gradientColors)
}
