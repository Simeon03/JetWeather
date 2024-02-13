package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.helpers.views.BoxGradientBg
import com.example.jetweather.viewmodel.Model
import com.example.jetweather.views.hourlyweather.layouts.HourlyWeatherInfo

@Composable
fun HourlyWeatherView(model: Model) {
    BoxGradientBg {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(24) { index ->
                HourlyWeatherInfo(model = model, index = index)
            }
        }
    }
}
