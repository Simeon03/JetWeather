package com.example.jetweather.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.currentDaylight.DaylightView
import com.example.jetweather.views.currentweather.CurrentWeatherView
import com.example.jetweather.views.dailyweather.DailyWeatherView
import com.example.jetweather.views.hourlyweather.HourlyWeatherView

@Composable
fun FullMainView(
    current: CurrentWeatherViewModel,
    weeklyWeatherViewModel: WeeklyWeatherViewModel,
    hourlyWeatherViewModel: HourlyWeatherViewModel
) {
    val isLoading by current.isLoading.collectAsState()

    if (!isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Gradient1)
        ) {
            LazyColumn(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item { CurrentWeatherView(viewModel = current) }
                item { DailyWeatherView(viewModel = weeklyWeatherViewModel) }
                item { HourlyWeatherView(viewModel = hourlyWeatherViewModel) }
                item { DaylightView(viewModel = current) }
            }
        }
    }
}