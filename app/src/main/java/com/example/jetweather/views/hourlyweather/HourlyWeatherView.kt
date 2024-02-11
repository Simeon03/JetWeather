package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.gradientBackground
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun HourlyWeatherView(viewModel: WeatherViewModel) {
    val weatherDataLoading by viewModel.isLoading.collectAsState()

    if (!weatherDataLoading) {
        Card(
            shape = RoundedCornerShape(12.dp),
        ) {
            Box(modifier = Modifier.fillMaxWidth().background(gradientBackground())) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(24) { index ->
                        HourlyWeatherStats(viewModel = viewModel, index = index)
                    }
                }
            }
        }
    }
}
