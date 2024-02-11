package com.example.jetweather.views.todaysun.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetweather.views.todaysun.subviews.labeltime.LabelTimeView

@Composable
fun FullSunsetSunriseStatsView(sunriseTime: String, sunsetTime: String) {
    val sunriseLabel = "Sunrise"
    val sunsetLabel = "Sunset"

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        LabelTimeView(label = sunriseLabel, time = sunriseTime)
        LabelTimeView(label = sunsetLabel, time = sunsetTime)
    }
}
