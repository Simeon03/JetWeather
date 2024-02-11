package com.example.jetweather.views.currentDaylight.texts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun LabelTime(
    labelText: String,
    timeText: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Label(text = labelText)
        Time(text = timeText)
    }
}