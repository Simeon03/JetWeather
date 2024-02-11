package com.example.jetweather.views.currentDaylight.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.views.currentDaylight.layouts.sublayouts.DaylightInfo
import com.example.jetweather.views.currentDaylight.layouts.sublayouts.DaylightProgressBar

@Composable
fun Daylight(
    sunriseTime: String,
    sunrisePercentage: Float,
    sunsetTime: String,
    sunsetPercentage: Float,
    currentTimePercentage: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DaylightInfo(
            sunriseTime = sunriseTime,
            sunsetTime = sunsetTime
        )
        DaylightProgressBar(
            sunrisePercentage = sunrisePercentage,
            sunsetPercentage = sunsetPercentage,
            currentTimePercentage = currentTimePercentage,
        )
    }
}
