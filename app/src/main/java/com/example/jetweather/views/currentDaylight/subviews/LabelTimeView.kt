package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun LabelTimeView(
    labelText: String,
    timeText: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LabelView(labelText = labelText)
        TimeView(timeText = timeText)
    }
}