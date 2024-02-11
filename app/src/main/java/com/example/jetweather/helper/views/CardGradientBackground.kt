package com.example.jetweather.helper.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2

@Composable
fun gradientBackground(): Brush {
    val gradientColors = listOf(
        Gradient1.copy(alpha = 0.5f),
        Gradient2.copy(alpha = 0.5f)
    )
    return Brush.verticalGradient(gradientColors)
}
