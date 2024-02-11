package com.example.jetweather.views.currentDaylight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.DataFormatter.formatTime
import com.example.jetweather.helper.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helper.DataFormatter.getPercentageOfDay
import com.example.jetweather.helper.views.CardWithGradientBackground
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.currentDaylight.layouts.Daylight

@Composable
fun DaylightView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val sunriseTime = formatTime(weatherData.currentSunriseTime)
    val sunrisePercentage = getPercentageOfDay(weatherData.currentSunriseTime)
    val sunsetTime = formatTime(weatherData.currentSunsetTime)
    val sunsetPercentage = getPercentageOfDay(weatherData.currentSunsetTime)
    val currentTimePercentage = getCurrentTimePercentage()

    CardWithGradientBackground {
        Daylight(
            sunriseTime = sunriseTime,
            sunrisePercentage = sunrisePercentage,
            sunsetTime = sunsetTime,
            sunsetPercentage = sunsetPercentage,
            currentTimePercentage = currentTimePercentage
        )
    }
}
