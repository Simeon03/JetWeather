package com.example.jetweather.views.weeklyweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.CardWithGradientBackground
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun FullDailyWeatherCardView(viewModel: WeatherViewModel) {
    CardWithGradientBackground {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
        ) {
            for (i in 0..6) {
                FullDailyWeatherView(viewModel = viewModel, index = i)
            }
        }
    }
}