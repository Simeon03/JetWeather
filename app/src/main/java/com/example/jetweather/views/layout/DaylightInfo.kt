package com.example.jetweather.views.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.views.text.DaylightLabelTime

@Composable
fun DaylightInfo(
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
        DaylightLabelTime(labelText = sunriseLabel, timeText = sunriseTime)
        DaylightLabelTime(labelText = sunsetLabel, timeText = sunsetTime)
    }
}
