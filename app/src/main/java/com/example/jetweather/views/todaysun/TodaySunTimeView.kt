package com.example.jetweather.views.todaysun

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helper.CardWithGradientBackground
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.helper.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun TodaySunTimeView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()
    val weatherDataLoading by viewModel.isLoading.collectAsState()

    val sunriseTime = DataFormatter.getTimeOfDay(weatherData.sunriseTime[0])
    val sunrisePercentage = DataFormatter.getPercentageOfDay(weatherData.sunriseTime[0])
    val sunsetTime = DataFormatter.getTimeOfDay(weatherData.sunsetTime[0])
    val sunsetPercentage = DataFormatter.getPercentageOfDay(weatherData.sunsetTime[0])
    val currentTimePercentage = getCurrentTimePercentage()

    if (!weatherDataLoading) {
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
}
