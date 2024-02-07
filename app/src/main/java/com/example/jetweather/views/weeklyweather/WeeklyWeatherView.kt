package com.example.jetweather.views.weeklyweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun WeeklyWeatherView(viewModel: WeatherViewModel) {
    val weatherDataLoading by viewModel.isLoading.collectAsState()

    if (!weatherDataLoading) { WeatherCardContent(viewModel) }
}

@Composable
fun WeatherCardContent(viewModel: WeatherViewModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(gradientBackground())) {
            Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                for (i in 0..6) {
                    WeeklyWeatherStats(viewModel = viewModel, index = i)
                }
            }
        }
    }
}

@Composable
fun gradientBackground(): Brush {
    val gradientColors = listOf(
        Gradient1.copy(alpha = 0.8f),
        Gradient2.copy(alpha = 0.7f)
    )
    return Brush.verticalGradient(gradientColors)
}
