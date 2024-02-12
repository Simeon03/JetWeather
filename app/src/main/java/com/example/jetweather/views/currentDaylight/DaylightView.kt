package com.example.jetweather.views.currentDaylight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.views.CardGradientBg
import com.example.jetweather.viewmodel.MainViewModel
import com.example.jetweather.views.currentDaylight.layouts.Daylight

@Composable
fun DaylightView(viewModel: MainViewModel) {
    val weatherDataText by viewModel.weatherDataText.collectAsState()

    CardGradientBg {
        Daylight(
            sunriseTime = weatherDataText.currentSunriseTime,
            sunrisePercentage = weatherDataText.currentSunrisePercentage,
            sunsetTime = weatherDataText.currentSunsetTime,
            sunsetPercentage = weatherDataText.currentSunsetPercentage,
            currentTimePercentage = getCurrentTimePercentage()
        )
    }
}
