package com.example.jetweather.views.currentDaylight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatTime
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay
import com.example.jetweather.helpers.views.CardGradientBg
import com.example.jetweather.viewmodel.MainViewModel
import com.example.jetweather.views.currentDaylight.layouts.Daylight

@Composable
fun DaylightView(viewModel: MainViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val sunriseTime = formatTime(weatherData.currentSunriseTime)
    val sunrisePercentage = getPercentageOfDay(weatherData.currentSunriseTime)
    val sunsetTime = formatTime(weatherData.currentSunsetTime)
    val sunsetPercentage = getPercentageOfDay(weatherData.currentSunsetTime)
    val currentTimePercentage = getCurrentTimePercentage()

    CardGradientBg {
        Daylight(
            sunriseTime = sunriseTime,
            sunrisePercentage = sunrisePercentage,
            sunsetTime = sunsetTime,
            sunsetPercentage = sunsetPercentage,
            currentTimePercentage = currentTimePercentage
        )
    }
}
