package com.example.jetweather.views.currentDaylight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.formatTime
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay
import com.example.jetweather.helpers.views.BoxGradientBg
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.currentDaylight.layouts.Daylight

@Composable
fun DaylightView(viewModel: CurrentWeatherViewModel) {
    val viewModel by viewModel.currentWeatherData.collectAsState()

    BoxGradientBg {
        Daylight(
            sunriseTime = formatTime(viewModel.sunriseTime),
            sunrisePercentage = getPercentageOfDay(viewModel.sunriseTime),
            sunsetTime = formatTime(viewModel.sunsetTime),
            sunsetPercentage = getPercentageOfDay(viewModel.sunsetTime),
            currentTimePercentage = getCurrentTimePercentage()
        )
    }
}
