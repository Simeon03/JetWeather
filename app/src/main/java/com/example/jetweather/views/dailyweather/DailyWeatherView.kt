package com.example.jetweather.views.dailyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helpers.views.BoxGradientBg
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.dailyweather.layouts.DailyWeatherInfo

@Composable
fun DailyWeatherView(viewModel: WeeklyWeatherViewModel) {
    BoxGradientBg {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0..6) {
                DailyWeatherInfo(index = i, viewModel = viewModel)
            }
        }
    }
}