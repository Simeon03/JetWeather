package com.example.jetweather.views.currentDaylight

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetweather.helpers.DataFormatter.fetchTime
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.views.currentDaylight.layouts.Daylight

@Composable
fun DaylightView(viewModel: CurrentWeatherViewModel) {
    val viewModel by viewModel.currentWeatherData.collectAsState()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Gradient2,
        ),
    ) {
        Daylight(
            sunriseTime = viewModel.sunriseTime.fetchTime(),
            sunrisePercentage = viewModel.sunriseTime.getPercentageOfDay(),
            sunsetTime = viewModel.sunsetTime.fetchTime(),
            sunsetPercentage = viewModel.sunsetTime.getPercentageOfDay(),
            currentTimePercentage = getCurrentTimePercentage()
        )
    }
}
