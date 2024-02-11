package com.example.jetweather.views.todaysun.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FullSunsetSunriseStatsView(sunriseTime: String, sunsetTime: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        LabelTimeView(label = "Sunrise", time = sunriseTime)
        LabelTimeView(label = "Sunset", time = sunsetTime)
    }
}
