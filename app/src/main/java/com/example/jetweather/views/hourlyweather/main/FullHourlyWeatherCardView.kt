package com.example.jetweather.views.hourlyweather.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.views.CardWithGradientBackground
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.hourlyweather.FullHourlyWeatherView

@Composable
fun FullHourlyWeatherCardView(viewModel: WeatherViewModel) {
    CardWithGradientBackground {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(24) { index ->
                FullHourlyWeatherView(viewModel = viewModel, index = index)
            }
        }
    }
}
