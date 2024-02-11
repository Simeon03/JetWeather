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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.ui.theme.Gradient3
import com.example.jetweather.ui.theme.Gradient4
import com.example.jetweather.ui.theme.Gradient5
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.currentweather.FullCurrentWeatherView
import com.example.jetweather.views.hourlyweather.FullHourlyWeatherCardView
import com.example.jetweather.views.currentDaylight.FullTodaySunTimeView
import com.example.jetweather.views.dailyweather.FullDailyWeatherCardView

@Composable
fun FullMainView(viewModel: WeatherViewModel) {
    val weatherDataLoading by viewModel.isLoading.collectAsState()

    val gradient = Brush.verticalGradient(
        colors = listOf(Gradient1, Gradient2, Gradient3, Gradient4, Gradient5),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        if (!weatherDataLoading) {
            LazyColumn(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item { FullCurrentWeatherView(viewModel = viewModel) }
                item { FullDailyWeatherCardView(viewModel = viewModel) }
                item { FullHourlyWeatherCardView(viewModel = viewModel) }
                item { FullTodaySunTimeView(viewModel = viewModel) }
            }
        }
    }
}