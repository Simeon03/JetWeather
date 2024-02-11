package com.example.jetweather.views.currentDaylight.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FullDaylightStatsView(
    sunriseTime: String,
    sunsetTime: String
) {
    val sunriseLabel = "Sunrise"
    val sunsetLabel = "Sunset"

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        LabelTimeView(labelText = sunriseLabel, timeText = sunriseTime)
        LabelTimeView(labelText = sunsetLabel, timeText = sunsetTime)
    }
}
