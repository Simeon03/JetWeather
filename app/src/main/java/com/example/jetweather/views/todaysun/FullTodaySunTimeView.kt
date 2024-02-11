package com.example.jetweather.views.todaysun

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.CardWithGradientBackground
import com.example.jetweather.helper.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helper.DataFormatter.getPercentageOfDay
import com.example.jetweather.helper.DataFormatter.getTimeOfDay
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun FullTodaySunTimeView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val sunriseTime = getTimeOfDay(weatherData.currentSunriseTime[0])
    val sunrisePercentage = getPercentageOfDay(weatherData.currentSunriseTime[0])
    val sunsetTime = getTimeOfDay(weatherData.currentSunsetTime[0])
    val sunsetPercentage = getPercentageOfDay(weatherData.currentSunsetTime[0])
    val currentTimePercentage = getCurrentTimePercentage()

    CardWithGradientBackground {
        TodaySunTimeStatsLayoutView(
            sunriseTime = sunriseTime,
            sunrisePercentage = sunrisePercentage,
            sunsetTime = sunsetTime,
            sunsetPercentage = sunsetPercentage,
            currentTimePercentage = currentTimePercentage
        )
    }
}
