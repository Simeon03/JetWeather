package com.example.jetweather.helper.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BoxGradientBg(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .background(cardGradientColors())
    ) {
        content()
    }
}
