package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TimeView(timeText: String) {
    Text(
        text = timeText,
        fontSize = 28.sp,
    )
}