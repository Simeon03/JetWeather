package com.example.jetweather.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.views.currentDaylight.DaylightView
import com.example.jetweather.views.hourlyweather.HourlyWeatherView

@Composable
fun DailyWeatherCards(
    hourly: HourlyWeatherViewModel,
    current: CurrentWeatherViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HourlyWeatherView(viewModel = hourly)
        DaylightView(viewModel = current)
    }
}
