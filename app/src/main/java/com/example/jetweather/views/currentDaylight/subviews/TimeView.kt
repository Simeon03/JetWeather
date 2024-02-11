package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TimeView(text: String) {
    val fontSize = 28.sp

    Text(
        text = text,
        fontSize = fontSize,
    )
}