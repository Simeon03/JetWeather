package com.example.jetweather.helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GradientBackgroundBox(content: @Composable () -> Unit) {
    Box(modifier = Modifier.background(gradientBackground())) {
        content()
    }
}
