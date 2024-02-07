package com.example.jetweather.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.ui.theme.Gradient3
import com.example.jetweather.ui.theme.Gradient4
import com.example.jetweather.ui.theme.Gradient5
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.currentweather.CurrentWeatherView
import com.example.jetweather.views.hourlyweather.HourlyWeatherView
import com.example.jetweather.views.weeklyweather.WeeklyWeatherView

@Composable
fun MainView(viewModel: WeatherViewModel) {
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
        LazyColumn(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item { CurrentWeatherView(viewModel = viewModel) }
            item { WeeklyWeatherView(viewModel = viewModel) }
            item { HourlyWeatherView(viewModel = viewModel) }
        }
    }
}