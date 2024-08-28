package com.example.jetweather.views.customprogressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ProgressBarContainer(
    barHeight: Dp,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(100)
    val bgColor = Color.DarkGray.copy(alpha = 0.9f)
    val contentAlignment = Alignment.CenterStart

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight)
            .clip(shape)
            .background(bgColor),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}
