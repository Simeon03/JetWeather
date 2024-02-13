package com.example.jetweather.helpers.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxGradientBg(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                brush = cardGradientColors(),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        content()
    }
}
