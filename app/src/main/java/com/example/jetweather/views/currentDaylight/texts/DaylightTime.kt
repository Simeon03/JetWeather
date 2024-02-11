package com.example.jetweather.views.currentDaylight.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun DaylightTime(text: String) {
    Text(
        text = text,
        fontSize = 28.sp,
    )
}